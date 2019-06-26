package xml_bsep.acc_service.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.model.Recension;
import com.eureka.common.model.RecensionStatus;

import xml_bsep.acc_service.UrlUtils;
import xml_bsep.acc_service.dto.RecensionDTO;
import xml_bsep.acc_service.service.AccomodationUnitService;
import xml_bsep.acc_service.service.UserService;

@RestController
@RequestMapping("")
public class RecensionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	AccomodationUnitService accUnitService;
	
	@Autowired
	UserService userService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/addRecension")
	public ResponseEntity addRecension(@RequestBody RecensionDTO recensionDTO) {
		
		Recension recension = new Recension();
		recension.setAccomodationUnit(accUnitService.findOne(recensionDTO.getAccUnitId()));
		recension.setComment(recensionDTO.getComment());
		recension.setRating(recensionDTO.getRating());
		recension.setStatus(RecensionStatus.PENDING);
		
		HttpEntity<Recension> request = new HttpEntity<Recension>(recension);
		getRT().exchange(UrlUtils.getRatingSystemUrl() + "/user/saveRecension", HttpMethod.POST, request, ResponseEntity.class);
		logger.info("NP_EVENT PO {} {} {}", userService.getCurrentUsername(), recensionDTO.getRating(), recensionDTO.getAccUnitId());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value = "/getRecensionsByAccUnitForUser")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnitForUser(@RequestBody long accId){
		
		HttpEntity<Long> request = new HttpEntity<Long>(accId);
		ResponseEntity<List<RecensionDTO>> response = getRT().
				exchange(UrlUtils.getRatingSystemUrl() + "/getRecensionsByAccUnit", HttpMethod.POST, request, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		
		if(response.getBody() == null) {
			logger.info("NP_EVENT POS {} {}", userService.getCurrentUsername(), accId);
			return new ResponseEntity<List<RecensionDTO>>(HttpStatus.OK);
		}
		
		for (RecensionDTO rDto : response.getBody()) {
			if(rDto.getStatus() == RecensionStatus.PENDING || rDto.getStatus() == RecensionStatus.DECLINED) {
				rDto.setComment("");
			}
		}
		logger.info("NP_EVENT POS {} {}", userService.getCurrentUsername(), accId);
		return new ResponseEntity<List<RecensionDTO>>(response.getBody(),HttpStatus.OK);
	}
	
	@PostMapping(value = "/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnit(@RequestBody long accId, HttpServletRequest requestAddr){
		String addr = requestAddr.getRemoteAddr().toString();
		HttpEntity<Long> request = new HttpEntity<Long>(accId);
		ResponseEntity<List<RecensionDTO>> response = getRT().
				exchange(UrlUtils.getRatingSystemUrl() + "/getRecensionsByAccUnit", HttpMethod.POST, request, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		logger.info("NP_EVENT POSN {} {}", addr, accId);
		return new ResponseEntity<List<RecensionDTO>>(response.getBody(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/getRecensions")
	public ResponseEntity<List<RecensionDTO>> getRecensionsForAdmin(){
		
		ResponseEntity<List<RecensionDTO>> response = getRT().
				exchange(UrlUtils.getRatingSystemUrl() + "/admin/getRecensionsForApproval", HttpMethod.GET, null, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		//logger.info("NP_EVENT POS {} {}", userService.getCurrentUsername(), accUnitService.);
		return new ResponseEntity<List<RecensionDTO>>(response.getBody(),HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/approveRecension")
	public ResponseEntity approveRecension(@RequestBody long id) {
		
		HttpEntity<Long> request = new HttpEntity<Long>(id);
		getRT().exchange(UrlUtils.getRatingSystemUrl() + "/admin/approveRecension", HttpMethod.POST, request, ResponseEntity.class);
		logger.info("NP_EVENT OK {} {}", userService.getCurrentUsername(), id);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/declineRecension")
	public ResponseEntity declineRecension(@RequestBody long id) {
		
		HttpEntity<Long> request = new HttpEntity<Long>(id);
		getRT().exchange(UrlUtils.getRatingSystemUrl() + "/admin/declineRecension", HttpMethod.POST, request, ResponseEntity.class);
		logger.info("NP_EVENT ZK {} {}", userService.getCurrentUsername(), id);
		return new ResponseEntity(HttpStatus.OK);
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
    
 }
