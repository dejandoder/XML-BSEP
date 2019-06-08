package xml_bsep.acc_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.AccomodationType;

import xml_bsep.acc_service.service.AccomodationTypeService;

@RestController
@RequestMapping("/")
public class AccomodationTypeController {

	@Autowired
	AccomodationTypeService service;
	
	@PostMapping(value = "/admin/addNewAccType", consumes = "application/json")
	public ResponseEntity<List<AccomodationType>> addNewAccType(@RequestBody AccomodationType accType){
		if(service.checkIfTypeExsists(accType.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//odje bi sada trebale neke sql parsiarnje provjere i te pizdarije
		service.save(accType);
		List<AccomodationType> accTypes = service.findAll();
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/removeAccType")
	public ResponseEntity<List<AccomodationType>> removeAccType(@RequestBody AccomodationType accType){
		//treba uraditi provjeru da li ima smjestaja sa zadatim tipom
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		List<AccomodationType> accTypes = service.delete(accType.getId());
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAccTypes")
	public ResponseEntity<List<AccomodationType>> getAllAccTypes(){
		List<AccomodationType> accTypes = service.findAll();
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
}