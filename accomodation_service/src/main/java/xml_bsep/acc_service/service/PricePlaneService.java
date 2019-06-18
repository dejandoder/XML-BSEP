package xml_bsep.acc_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.PricePlan;

import xml_bsep.acc_service.repository.PricePlanRepository;

@Service
public class PricePlaneService {

	@Autowired
	PricePlanRepository repository;
	
	public PricePlan save(PricePlan pp) {
		return repository.save(pp);
	}
	
}
