package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.AccomodationType;
import xml_bsep.agent_app.repository.AccomodationTypeRepository;

@Service
public class AccomodationTypeService {

	@Autowired
	AccomodationTypeRepository repository;
	
	public List<AccomodationType> findAll(){
		return repository.findAll();
	}
	
}
