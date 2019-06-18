package xml_bsep.reservation_service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.reservation_service.dto.CheckReaservationDTO;
import xml_bsep.reservation_service.service.ReservationService;

@RestController
@RequestMapping("/")
public class ReservationController {

	@Autowired
	ReservationService resService;
	
	@PostMapping(value = "/checkIfAccUnitIsAvalible")
	public ResponseEntity<Boolean> checkIfAccUnitIsAvalible(@RequestBody CheckReaservationDTO checkReservation){
		boolean availabile = resService.checkIfAccUnitIsAvalible(checkReservation);
		return new ResponseEntity<>(availabile, HttpStatus.OK);
	}
	
}
