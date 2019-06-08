package xml_bsep.agent_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.model.AgentReservationRequest;
import xml_bsep.agent_app.model.AgentReservationResponse;
import xml_bsep.agent_app.model.ApproveReservationRequest;
import xml_bsep.agent_app.model.ApproveReservationResponse;
import xml_bsep.agent_app.model.ConfirmReservationRequest;
import xml_bsep.agent_app.model.ConfirmReservationResponse;
import xml_bsep.agent_app.model.SyncReservationsRequest;
import xml_bsep.agent_app.model.SyncReservationsResponse;
import xml_bsep.agent_app.soap_clients.ReservationServiceSoapClient;

@RestController
@RequestMapping("")
public class ReservationController {
	
	@Autowired
	ReservationServiceSoapClient client;
	
	@PostMapping(value = "/syncReservations")
	public ResponseEntity<SyncReservationsResponse> syncResvations(){
		return new ResponseEntity<>(client.syncReservation(new SyncReservationsRequest()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/approveReservation")
	public ResponseEntity<ApproveReservationResponse> approveReservation(){
		return new ResponseEntity<>(client.approveReservation(new ApproveReservationRequest()), HttpStatus.OK);
	}

	@PostMapping(value = "/confirmReservation")
	public ResponseEntity<ConfirmReservationResponse> confirmReservation(){
		return new ResponseEntity<>(client.confirmReservation(new ConfirmReservationRequest()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/agentReservation")
	public ResponseEntity<AgentReservationResponse> agentReservation(){
		return new ResponseEntity<>(client.agentReservation(new AgentReservationRequest()), HttpStatus.OK);
	}
	
}
