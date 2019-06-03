package xml_bsep.admin_service.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.admin_service.service.AccomodationTypeService;
import com.eureka.common.model.AccomodationType;

@RestController
@RequestMapping("/")
public class AccomodationTypeController {

	@Autowired
	AccomodationTypeService service;
	
	@PostMapping(value = "/addNewAccType", consumes = "application/json")
	public ResponseEntity<List<AccomodationType>> addNewAccType(@RequestBody AccomodationType accType){
		
		//odje bi sada trebale neke sql parsiarnje provjere i te pizdarije
		service.save(accType);
		List<AccomodationType> accTypes = service.findAll();
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/removeAccType")
	public ResponseEntity<List<AccomodationType>> removeAccType(@PathParam("id") long id){
		List<AccomodationType> accTypes = service.delete(id);
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
}