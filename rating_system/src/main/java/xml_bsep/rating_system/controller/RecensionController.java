package xml_bsep.rating_system.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xml_bsep.rating_system.dto.CheckReviewDTO;
import xml_bsep.rating_system.dto.RecensionDTO;
import xml_bsep.rating_system.model.AccomodationUnit;
import xml_bsep.rating_system.model.Recension;
import xml_bsep.rating_system.model.User;
import xml_bsep.rating_system.service.RecesnsionService;
import xml_bsep.rating_system.service.UserService;

@RestController
@RequestMapping("")
public class RecensionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RecesnsionService service;

	@Autowired
	UserService userService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/addUser")
	public ResponseEntity addUser(@RequestBody User user) {
		service.addUser(user);
		logger.info("NP_EVENT DA {} {}", userService.getCurrentUsername(), user.getId());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/agent/addAccUnit")
	public ResponseEntity addAccUnit(@RequestBody AccomodationUnit accUnit) {
		service.addAcomodationUnit(accUnit);
		logger.info("NP_EVENT DSJ {} {}", userService.getCurrentUsername(), accUnit.getId()); 
	    return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/getRecensionsForApproval")
	public ResponseEntity<List<RecensionDTO>> getRecensionsForApproval(){
		return new ResponseEntity<List<RecensionDTO>>(service.getRecensionsForApproval(), HttpStatus.OK);
	}

	@PostMapping(value = "/all/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnit(@RequestBody long id){
		logger.info("NP_EVENT POS {} {}", userService.getCurrentUsername(), id);
		return new ResponseEntity<>(service.getRecensionsByAccUnit(id),HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/user/saveRecension")
	public ResponseEntity saveRecension(@RequestBody Recension rec) {
		service.save(rec);
		logger.info("NP_EVENT OK {} {}", userService.getCurrentUsername(), rec.getId());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/approveRecension")
	public ResponseEntity approveRecension(@RequestBody long recId){
		service.approveRecension(recId);
		logger.info("NP_EVENT OK {} {}", userService.getCurrentUsername(), recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/declineRecension")
	public ResponseEntity declineRecension(@RequestBody long recId){
		service.declineRecension(recId);
		logger.info("NP_EVENT ZK {} {}", userService.getCurrentUsername(), recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value = "/all/checkRecension")
	public ResponseEntity<Boolean> checkRecension(@RequestBody CheckReviewDTO crDTO){
		return new ResponseEntity<>(service.checkIfRecnsionExsists(crDTO), HttpStatus.OK);
	}
	
}
