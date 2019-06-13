package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.AccomodationService;
import xml_bsep.agent_app.repository.AccomodationServiceRepositroy;

@Service
public class AccomodationServicesService {

	@Autowired
	AccomodationServiceRepositroy repositroy;
	
	public List<AccomodationService> findAll(){
		return repositroy.findAll();
	}
	
}
