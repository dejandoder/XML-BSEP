package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.dto.PricePlanDTO;
import xml_bsep.agent_app.model.PricePlan;
import xml_bsep.agent_app.repository.PricePlanRepository;

@Service
public class PricePlaneService {

	@Autowired
	PricePlanRepository repository;
	
	public List<PricePlanDTO> getPricePlansByAccomodationUnit(long id){
		List<PricePlan> plans = repository.getPricePlansByAccomodationUnit(id);
		List<PricePlanDTO> plansDTO = new ArrayList<>();
		for (PricePlan pricePlan : plans) {
			plansDTO.add(new PricePlanDTO(pricePlan));
		}
		return plansDTO;
	}
	
	public PricePlan save(PricePlan plan) {
		return repository.save(plan);
	}
}
