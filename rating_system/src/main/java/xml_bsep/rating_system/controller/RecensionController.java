package xml_bsep.rating_system.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.rating_system.model.Recension;
import xml_bsep.rating_system.model.RecensionStatus;

import xml_bsep.rating_system.dto.CheckReviewDTO;
import xml_bsep.rating_system.dto.RecensionDTO;
import xml_bsep.rating_system.service.AccUnitService;
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
	
	@Autowired
	AccUnitService accUnitService;
	
	@GetMapping(value = "/admin/getRecensions")
	public ResponseEntity<List<RecensionDTO>> getRecensionsForApproval(){
		return new ResponseEntity<List<RecensionDTO>>(service.getRecensionsForApproval(), HttpStatus.OK);
	}

	
	@PostMapping(value = "/all/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnit(@RequestBody @Min(1) long accId){
		List<RecensionDTO> recensions = service.getRecensionsByAccUnit(accId);
		return new ResponseEntity<List<RecensionDTO>>(recensions,HttpStatus.OK);
	}
	
	@PostMapping(value = "/all/getRecensionsByAccUnitForUser")
	public ResponseEntity<List<RecensionDTO>> getRecensionsByAccUnitForUser(@RequestBody @Min(1) long accId){
		List<RecensionDTO> recensions = service.getRecensionsByAccUnit(accId);
		
		for (RecensionDTO rDto : recensions) {
			if(rDto.getStatus() == RecensionStatus.PENDING || rDto.getStatus() == RecensionStatus.DECLINED) {
				rDto.setComment("");
			}
		}
		logger.info("NP_EVENT POS {} {}", userService.getCurrentUsername(), accId);
		return new ResponseEntity<List<RecensionDTO>>(recensions,HttpStatus.OK);
	}
	
	
	@SuppressWarnings("rawtypes")
	@PostMapping( value = "/user/addRecension")
	public ResponseEntity saveRecension( @RequestBody RecensionDTO recensionDTO) {
		Recension recension = new Recension();
		
		recension.setAccomodationUnit(accUnitService.finOneById(recensionDTO.getAccUnitId()));
		recension.setComment(recensionDTO.getComment());
		recension.setRating(recensionDTO.getRating());
		recension.setStatus(RecensionStatus.PENDING);
		recension.setUser(userService.getCurrentUser());
		
		service.save(recension);
		
		logger.info("NP_EVENT PO {} {} {}", userService.getCurrentUsername(), recensionDTO.getRating(), recensionDTO.getAccUnitId());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('APPROVE_RECENSION')")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/approveRecension")
	public ResponseEntity approveRecension(@RequestBody @Min(1) long recId){
		service.approveRecension(recId);
		logger.info("NP_EVENT OK {} {}", userService.getCurrentUsername(), recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//@PreAuthorize("hasAuthority('DECLINE_RECENSION')")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/admin/declineRecension")
	public ResponseEntity declineRecension(@RequestBody @Min(1) long recId){
		service.declineRecension(recId);
		logger.info("NP_EVENT ZK {} {}", userService.getCurrentUsername(), recId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping(value = "/all/checkRecension")
	public ResponseEntity<Boolean> checkRecension( @Valid @RequestBody CheckReviewDTO crDTO){
		return new ResponseEntity<>(service.checkIfRecnsionExsists(crDTO), HttpStatus.OK);
	}
	
}
