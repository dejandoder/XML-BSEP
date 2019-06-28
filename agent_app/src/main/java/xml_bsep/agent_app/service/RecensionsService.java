package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.Recension;
import xml_bsep.agent_app.model.SyncRecensionsResponse;
import xml_bsep.agent_app.repository.RecensionRepository;
import xml_bsep.agent_app.soap_clients.RatingServiceSoapClient;

@Service
public class RecensionsService {

	@Autowired
	RecensionRepository repository;
	
	@Autowired
	RatingServiceSoapClient soapClient;
	
	public List<Recension> getRecensionsByAccUnit(long id){
		return repository.getRecensionsByAccUnit(id);
	}
	
	public void syncRecensions() {
		SyncRecensionsResponse response = soapClient.syncRecensions();
		if(response.getRecensions() == null) return;
		repository.saveAll(response.getRecensions());
	}
}
