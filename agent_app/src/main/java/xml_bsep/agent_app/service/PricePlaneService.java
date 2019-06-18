package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.dto.PricePlanDTO;
import xml_bsep.agent_app.model.PricePlan;
import xml_bsep.agent_app.repository.PricePlanRepository;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;

@Service
public class PricePlaneService {

	@Autowired
	PricePlanRepository repository;
	
	@Autowired
	AccomodationServiceSoapClient soapClient;
	
	public List<PricePlanDTO> getPricePlansByAccomodationUnit(long id){
		List<PricePlan> plans = repository.getPricePlansByAccomodationUnit(id);
		List<PricePlanDTO> plansDTO = new ArrayList<>();
		for (PricePlan pricePlan : plans) {
			plansDTO.add(new PricePlanDTO(pricePlan));
		}
		return plansDTO;
	}
	
	@Transactional
	public PricePlan save(PricePlan plan){
		
		int errorCount = repository.checkIfPlanIsValid(plan.getFromDate(), plan.getToDate());	
		if(errorCount != 0) return null;
	
		PricePlan plan2 = repository.save(plan);		
		soapClient.addPricePlan(plan2);
		
		return plan;
	}
}
