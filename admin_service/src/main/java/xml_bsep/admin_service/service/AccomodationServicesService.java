package xml_bsep.admin_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.admin_service.repository.AccomodationServiceRepository;

@Service
public class AccomodationServicesService {
	
	@Autowired
	AccomodationServiceRepository repository;
	
	public com.eureka.common.model.AccomodationService save(com.eureka.common.model.AccomodationService accService) {
		return repository.save(accService);
	}
	
	public List<com.eureka.common.model.AccomodationService> findAll(){
		return repository.findAll();
	}
}
