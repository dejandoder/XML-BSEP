package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import xml_bsep.agent_app.UrlUtils;
import xml_bsep.agent_app.dto.AccomodationUnitDTO;
import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.AccomodationUnitRepository;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;

@Service
public class AccomodationUnitService {

	@Autowired
	AccomodationUnitRepository repository;
	
	@Autowired
	AccomodationServiceSoapClient accSoap;
	
	@Autowired 
	RestTemplate restTemplate;
	
	
	public AccomodationUnit save(AccomodationUnit accUnit) {
		repository.save(accUnit);
		accSoap.addNewAccomodationUnit(accUnit);

		HttpEntity<AccomodationUnit> request = new HttpEntity<AccomodationUnit>(accUnit);
		restTemplate.exchange(UrlUtils.getRatingSystemUrl() + "/agent/addAccUnit", HttpMethod.POST, request, ResponseEntity.class);
		
		
		return accUnit;
	}

	public List<AccomodationUnitDTO> getAccUnitsByAgent(User agent){
		
		List<AccomodationUnit> accUnits = repository.getAccUnitsByAgent(agent.getId());
		
		if(accUnits == null) return null;
		
		List<AccomodationUnitDTO> accUnitsDTO = new ArrayList<>();
		
		for (AccomodationUnit accUnit : accUnits) {
			accUnitsDTO.add(new AccomodationUnitDTO(accUnit));
		}
		
		return accUnitsDTO;
	}
	
	public AccomodationUnit getOne(long id) {
		return repository.findOneById(id);
	}
	
	public List<AccomodationUnitDTO> getAll(){
		List<AccomodationUnit> accUnits = repository.findAll();
		
		if(accUnits == null) return null;
		
		List<AccomodationUnitDTO> accUnitsDTO = new ArrayList<>();
		
		for (AccomodationUnit accUnit : accUnits) {
			accUnitsDTO.add(new AccomodationUnitDTO(accUnit));
		}
		
		return accUnitsDTO;
	}
}

