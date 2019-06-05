package xml_bsep.admin_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.AccomodationService;

import xml_bsep.admin_service.service.AccomodationServicesService;

@RestController
@RequestMapping("/")
public class AccomodationServiceController {

	@Autowired
	AccomodationServicesService service;
	
	@PostMapping(value = "/addAccService")
	public ResponseEntity<List<AccomodationService>> addNewAccService(@RequestBody AccomodationService accService){
		if(service.checkIfServicesExsist(accService.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//sranje sa provjerama ako treba
		service.save(accService);
		List<AccomodationService> accServices = service.findAll();
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAccServices")
	public ResponseEntity<List<AccomodationService>> getAllAccService(){
		List<AccomodationService> accServices = service.findAll();
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
	
	@PostMapping(value = "/removeAccService")
	public ResponseEntity<List<AccomodationService>> removeAccService(@RequestBody AccomodationService accService){
		//prvojeriti da li se moze obrisati accService, jer da neki hotel ima mozda
		List<AccomodationService> accServices = service.delete(accService.getId());
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
}
