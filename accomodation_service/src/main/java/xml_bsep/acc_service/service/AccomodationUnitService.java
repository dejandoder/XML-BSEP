package xml_bsep.acc_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.AccomodationUnit;

import xml_bsep.acc_service.repository.AccomodationUnitRepository;

@Service
public class AccomodationUnitService {

	@Autowired
	AccomodationUnitRepository repository;
	
	public AccomodationUnit save(AccomodationUnit accUnit) {
		return repository.save(accUnit);
	}
	
}
