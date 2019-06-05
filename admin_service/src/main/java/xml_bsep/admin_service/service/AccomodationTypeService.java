package xml_bsep.admin_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.AccomodationType;

import xml_bsep.admin_service.repository.AccomodationTypeRepository;

@Service
public class AccomodationTypeService {
	
	@Autowired
	AccomodationTypeRepository repository;
	
	public AccomodationType save(AccomodationType ac) {
		return repository.save(ac);
	}
	
	public List<AccomodationType> findAll(){
		return repository.findAll();
	}
	
	public List<AccomodationType> delete(long id){
		repository.delete(id);
		return repository.findAll();
	}
	
	public boolean checkIfTypeExsists(String name) {
		if(repository.findAccomodationTypeByName(name).isEmpty()) return false;
		else return true;
	}
}


