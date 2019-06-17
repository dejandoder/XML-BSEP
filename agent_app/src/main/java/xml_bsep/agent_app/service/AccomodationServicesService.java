package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.AccomodationService;
import xml_bsep.agent_app.model.SyncAccServicesResponse;
import xml_bsep.agent_app.repository.AccomodationServiceRepositroy;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;

@Service
public class AccomodationServicesService {

	@Autowired
	AccomodationServiceRepositroy repositroy;
	
	@Autowired
	AccomodationServiceSoapClient soapClient;
	
	public List<AccomodationService> findAll(){
		return repositroy.findAll();
	}

	public void syncServices() {
		SyncAccServicesResponse respones = soapClient.syncAccService();
		repositroy.saveAll(respones.getService());
	}
	
	
}
