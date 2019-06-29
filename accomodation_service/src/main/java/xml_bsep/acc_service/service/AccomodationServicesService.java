package xml_bsep.acc_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.acc_service.model.AccomodationService;

import xml_bsep.acc_service.repository.AccomodationServiceRepository;


@Service
public class AccomodationServicesService {
	
	@Autowired
	AccomodationServiceRepository repository;
	
	public xml_bsep.acc_service.model.AccomodationService save(xml_bsep.acc_service.model.AccomodationService accService) {
		return repository.save(accService);
	}
	
	public List<xml_bsep.acc_service.model.AccomodationService> findAll(){
		return repository.findAll();
	}
	
	public List<xml_bsep.acc_service.model.AccomodationService> delete(long id){
		repository.delete(id);
		return repository.findAll();
	}
	
	public boolean checkIfServicesExsist(String name) {
		if(repository.findAccomodationServiceByName(name).isEmpty()) return false;
		else return true;
	}
	
	public AccomodationService findOne(long id) {
		return repository.findOneById(id);
	}
}
