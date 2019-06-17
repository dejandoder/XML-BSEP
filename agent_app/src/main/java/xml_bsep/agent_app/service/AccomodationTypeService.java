package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.AccomodationType;
import xml_bsep.agent_app.model.SyncAccomodationTypeResponse;
import xml_bsep.agent_app.repository.AccomodationTypeRepository;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;

@Service
public class AccomodationTypeService {

	@Autowired
	AccomodationTypeRepository repository;
	
	@Autowired
	AccomodationServiceSoapClient soapClient;
	
	
	public List<AccomodationType> findAll(){
		return repository.findAll();
	}
	
	public void syncTypes() {
		SyncAccomodationTypeResponse response = soapClient.syncAccomodationTypes();
		repository.saveAll(response.getTypes());
	}
}
