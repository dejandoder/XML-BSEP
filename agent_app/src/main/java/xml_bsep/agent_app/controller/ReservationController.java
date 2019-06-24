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

import xml_bsep.agent_app.dto.ReservationDTO;
import xml_bsep.agent_app.model.SyncReservationsResponse;
import xml_bsep.agent_app.service.ReservationService;
import xml_bsep.agent_app.soap_clients.ReservationServiceSoapClient;

@RestController
@RequestMapping("")
@Validated
public class ReservationController {
	
	@Autowired
	ReservationServiceSoapClient client;
	
	@Autowired
	ReservationService resService;
	
	@PostMapping(value = "/syncReservations")
	public ResponseEntity<SyncReservationsResponse> syncResvations(){
		return new ResponseEntity<>(client.syncReservation(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/approveReservation")
	public ResponseEntity approveReservation(@RequestBody @Min(1) long resId){
		if(resService.approveReservation(resId)) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/confirmReservation")
	public ResponseEntity confirmReservation(@RequestBody @Min(1) long resId){
		if(resService.confirmReservation(resId)) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/declineReservation")
	public ResponseEntity declineReservation(@RequestBody @Min(1) long resId){
		if(resService.declineReservation(resId)) return new ResponseEntity(HttpStatus.OK);
		else return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/agentReservation")
	public ResponseEntity agentReservation(@RequestBody @Valid ReservationDTO resDTO){
		resService.doAgentReservation(resDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}	
	
	@PostMapping(value = "/getReservationsByAccUnit")
	public ResponseEntity<List<ReservationDTO>> getReservationsByAccUnit(@RequestBody @Min(1) long accId){
		List<ReservationDTO> retVal = resService.getReservationsByAccUnit(accId);
		return new ResponseEntity<List<ReservationDTO>>(retVal,HttpStatus.OK);
	}
	
}
