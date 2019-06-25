package xml_bsep.acc_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.AccomodationType;
import com.eureka.common.model.AccomodationUnit;

import xml_bsep.acc_service.service.AccomodationTypeService;
import xml_bsep.acc_service.service.AccomodationUnitService;

@RestController
@RequestMapping("/")
public class AccomodationTypeController {

	@Autowired
	AccomodationTypeService service;
	
	@Autowired
	AccomodationUnitService accUnitService;
	
	@PostMapping(value = "/admin/addNewAccType", consumes = "application/json")
	public ResponseEntity<List<AccomodationType>> addNewAccType(@Valid @RequestBody AccomodationType accType){
		if(service.checkIfTypeExsists(accType.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//odje bi sada trebale neke sql parsiarnje provjere i te pizdarije
		service.save(accType);
		List<AccomodationType> accTypes = service.findAll();
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/removeAccType")
	public ResponseEntity<List<AccomodationType>> removeAccType(@Valid @RequestBody AccomodationType accType){
		//treba uraditi provjeru da li ima smjestaja sa zadatim tipom
		
		for(AccomodationUnit accUnit : accUnitService.findAll()){
			if(accUnit.isType(accType)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<AccomodationType> accTypes = service.delete(accType.getId());
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/getAllAccTypes")
	public ResponseEntity<List<AccomodationType>> getAllAccTypes(){
		List<AccomodationType> accTypes = service.findAll();
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
}