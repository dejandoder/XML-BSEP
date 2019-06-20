package xml_bsep.agent_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.service.AccomodationServicesService;
import xml_bsep.agent_app.service.AccomodationTypeService;
import xml_bsep.agent_app.service.MessageService;
import xml_bsep.agent_app.service.ReservationService;
import xml_bsep.agent_app.service.UserService;

@RestController
@RequestMapping("/")
public class RandomController {

	@Autowired
	UserService userService;
	
	@Autowired
	AccomodationServicesService accServicesService;
	
	@Autowired
	AccomodationTypeService typesService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	ReservationService reservationService;
	
	
	@GetMapping(value = "/logOut")
	public void logOut() {
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/syncUsers")
	public ResponseEntity sync() {
		userService.syncUsers();
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/syncAll")
	public ResponseEntity syncAfterLogin() {
		accServicesService.syncServices();
		typesService.syncTypes();
		//messageService.syncMessages();
		reservationService.syncReservations();
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
