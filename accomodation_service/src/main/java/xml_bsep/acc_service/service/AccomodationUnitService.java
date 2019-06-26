package xml_bsep.acc_service.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.model.AccomodationUnit;
import com.eureka.common.model.Image;

import xml_bsep.acc_service.UrlUtils;
import xml_bsep.acc_service.dto.AccomodationUnitDTO;
import xml_bsep.acc_service.dto.CheckReaservationDTO;
import xml_bsep.acc_service.dto.RecensionDTO;
import xml_bsep.acc_service.dto.SearchDTO;
import xml_bsep.acc_service.repository.AccomodationUnitRepository;
import xml_bsep.acc_service.repository.ImageRepository;
import xml_bsep.acc_service.repository.PricePlanRepository;

@Service
public class AccomodationUnitService {

	@Autowired
	AccomodationUnitRepository repository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PricePlanRepository ppRepository;
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	UserService userService;
	
	public AccomodationUnit save(AccomodationUnit accUnit) {
		return repository.save(accUnit);
	}
	
	public ArrayList<AccomodationUnitDTO> search(SearchDTO searchDTO){
		
		List<AccomodationUnit> searchResults = repository.searchAccUnits(searchDTO.getPersons());
		
		List<AccomodationUnit> searchResults1 = new ArrayList<>();
		
		for (AccomodationUnit accomodationUnit : searchResults) {
			int pom = 0;
			
			if(searchDTO.getCategory() != -1 && searchDTO.getCategory() != -0) {
				if(accomodationUnit.getCategory() != searchDTO.getCategory()) pom++;
			}
			
			if(searchDTO.getCancelationPeriod() > 0) {
				if(accomodationUnit.getCancelingPeriod() < searchDTO.getCancelationPeriod()) pom++;
			}
			
			if(searchDTO.getType().getId() != -1) {
				if(accomodationUnit.getAccomodationType().getId() != searchDTO.getType().getId()) pom++;
			}
			
			if(pom == 0) searchResults1.add(accomodationUnit);
		}
		
		List<AccomodationUnit> searchResults2 = new ArrayList<>();
		for(AccomodationUnit accUnit : searchResults1) {
			if(accUnit.getServices().containsAll(searchDTO.getServices())) searchResults2.add(accUnit);
		}
		
		if(searchDTO.getMaxDistance() == 0) searchDTO.setMaxDistance(100);
		
		List<AccomodationUnit> searchResults3 = new ArrayList<>();
		for(AccomodationUnit accUnit : searchResults2) {
			float lat1 = accUnit.getLocation().getLattitude();
			float lon1 = accUnit.getLocation().getLongitude();
			float lat2 = searchDTO.getLocation().getLattitude();
			float lon2 = searchDTO.getLocation().getLongitude();
			if(distance(lat1, lat2, lon1, lon2, 0.0, 0.0)/1000 <= Math.min(searchDTO.getMaxDistance(), 100)) {
				searchResults3.add(accUnit);
			}
		}
		
		List<AccomodationUnit> searchResults4 = new ArrayList<>();
		for(AccomodationUnit accUnit : searchResults3) {
			CheckReaservationDTO checkDTO = new CheckReaservationDTO();
			checkDTO.setAccID(accUnit.getId());
			checkDTO.setDates(searchDTO.getDates());
			
			HttpEntity<CheckReaservationDTO> request = new HttpEntity<CheckReaservationDTO>(checkDTO);
			ResponseEntity<Boolean> response = restTemplate.exchange("http://reservation-service/all/checkIfAccUnitIsAvalible", HttpMethod.POST, request, Boolean.class);
			
			if(response.getBody() == true) searchResults4.add(accUnit);
		}
		
		List<AccomodationUnitDTO> finalResults = new ArrayList<>();
		
		for(AccomodationUnit accUnit : searchResults4) {
			AccomodationUnitDTO accUnitDTO = new AccomodationUnitDTO(accUnit);
			float lat1 = accUnit.getLocation().getLattitude();
			float lon1 = accUnit.getLocation().getLongitude();
			float lat2 = searchDTO.getLocation().getLattitude();
			float lon2 = searchDTO.getLocation().getLongitude();
			accUnitDTO.setDistance(distance(lat1, lat2, lon1, lon2, 0.0, 0.0)/1000);
			
			float price = 0;
			
			for (Date dateIter = searchDTO.getDates().get(0); !dateIter.after(searchDTO.getDates().get(1)); dateIter = DateUtils.addDays(dateIter, 1)) {
		        Object o = ppRepository.getPricePlanForDay(dateIter, accUnit.getId());
		        if(o != null) {
		        	price+= (Float)o;
		        }
		    }
			
			accUnitDTO.setPrice(price);
			
			for (Image img : imageRepository.getImagesByAccUnit(accUnit.getId())) {
				accUnitDTO.addImage(Base64Utils.encodeToString(img.getPic()));
			}
			
			accUnitDTO.setRating(getRating(accUnit.getId()));
			
			finalResults.add(accUnitDTO);
		}
		
		
		return (ArrayList<AccomodationUnitDTO>) finalResults;
	}
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
	
	public AccomodationUnit findOne(long accId) {
		return repository.findOneById(accId);
	}
	
	private float getRating(long accId) {

		HttpEntity<Long> request = new HttpEntity<Long>(accId);
		ResponseEntity<List<RecensionDTO>> response = getRT().
				exchange(UrlUtils.getRatingSystemUrl() + "/all/getRecensionsByAccUnit", HttpMethod.POST, request, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		
		float sum = 0;
		for (RecensionDTO recensionDTO : response.getBody()) {
			sum+= recensionDTO.getRating();
		}
		return sum/response.getBody().size();
	}
	
	private RestTemplate getRT() {
		RestTemplate restTemplate = new RestTemplate();
	    restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor(){
	        @Override
	        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
	            request.getHeaders().set("Authorization", "Bearer " + userService.getJwtToken());//Set the header for each request
	            return execution.execute(request, body);
	        }
	    });
	    return restTemplate;
	}

	public List<AccomodationUnit> findAll(){
		return repository.findAll();
	}
}
