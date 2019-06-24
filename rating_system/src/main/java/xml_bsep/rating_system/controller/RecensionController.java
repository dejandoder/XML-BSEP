package xml_bsep.rating_system.controller;

import java.util.List;

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

	@Autowired
	RecesnsionService service;

	@Autowired
	UserService userService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/addUser")
	public ResponseEntity addUser(@RequestBody User user) {
		service.addUser(user);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/agent/addAccUnit")
	public ResponseEntity addAccUnit(@RequestBody AccomodationUnit accUnit) {
		service.addAcomodationUnit(accUnit);
	    return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/getRecensionsForApproval")
	public ResponseEntity<List<RecensionDTO>> getRecensionsForApproval(){
		return new ResponseEntity<List<RecensionDTO>>(service.getRecensionsForApproval(), HttpStatus.OK);
	}

	@PostMapping(value = "/all/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnit(@RequestBody long id){
		return new ResponseEntity<>(service.getRecensionsByAccUnit(id),HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/user/saveRecension")
	public ResponseEntity saveRecension(@RequestBody Recension rec) {
		service.save(rec);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/approveRecension")
	public ResponseEntity approveRecension(@RequestBody long recId){
		service.approveRecension(recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/declineRecension")
	public ResponseEntity declineRecension(@RequestBody long recId){
		service.declineRecension(recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value = "/all/checkRecension")
	public ResponseEntity<Boolean> checkRecension(@RequestBody CheckReviewDTO crDTO){
		return new ResponseEntity<>(service.checkIfRecnsionExsists(crDTO), HttpStatus.OK);
	}
	
}
