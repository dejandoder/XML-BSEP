package xml_bsep.agent_app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.dto.PricePlanDTO;
import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.PricePlan;
import xml_bsep.agent_app.service.AccomodationUnitService;
import xml_bsep.agent_app.service.PricePlaneService;

@RestController
@RequestMapping("")
@Validated
public class PricePlanController {
	
	@Autowired
	PricePlaneService ppService;
	
	@Autowired
	AccomodationUnitService accUnitService;
	
	@PostMapping("/getPricePlans")
	public ResponseEntity<List<PricePlanDTO>> getPricePlansByAccomodationUnit(@RequestBody @Min(1) long id){
		List<PricePlanDTO> plans = ppService.getPricePlansByAccomodationUnit(id);
		return new ResponseEntity<>(plans, HttpStatus.OK);
	};	
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/savePricePlan")
	public ResponseEntity savePricePlan(@RequestBody @Valid PricePlanDTO planDTO) {
		
		//provjera sa bekendom mikroservisa i sinhrnoizacija
		
		AccomodationUnit accUnit = accUnitService.getOne(planDTO.getAccID());
		
		PricePlan plan = new PricePlan();
		plan.setFromDate(planDTO.getFromDate());
		plan.setToDate(planDTO.getToDate());
		plan.setPricePerNight(planDTO.getPricePerNight());
		plan.setAccomodationUnit(accUnit);
		
		if(ppService.save(plan) == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(HttpStatus.OK);
	}
 }
