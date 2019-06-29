package xml_bsep.rating_system.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.rating_system.model.AccomodationUnit;

import xml_bsep.rating_system.repository.AccomodationUnitRepository;

@Service
public class AccUnitService {

	@Autowired
	AccomodationUnitRepository accRepository;
	
	public AccomodationUnit finOneById(long id) {
		return accRepository.findOneById(id);
	}
	
}
