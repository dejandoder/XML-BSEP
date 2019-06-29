package xml_bsep.reservation_service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml_bsep.reservation_service.service.ReservationService;

import xml_bsep.reservation_service.model.AgentReservationRequest;
import xml_bsep.reservation_service.model.AgentReservationResponse;
import xml_bsep.reservation_service.model.ApproveReservationRequest;
import xml_bsep.reservation_service.model.ApproveReservationResponse;
import xml_bsep.reservation_service.model.ConfirmReservationRequest;
import xml_bsep.reservation_service.model.ConfirmReservationResponse;
import xml_bsep.reservation_service.model.DeclineReservationRequest;
import xml_bsep.reservation_service.model.Reservation;
import xml_bsep.reservation_service.model.SOAPResponseStatus;
import xml_bsep.reservation_service.model.SyncReservationsRequest;
import xml_bsep.reservation_service.model.SyncReservationsResponse;

@Endpoint
public class ReservationEndpoint {

	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/hotel-team1";
	
	@Autowired
	ReservationService service;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sync_reservations_request")
	public SyncReservationsResponse syncReservations(@RequestPayload SyncReservationsRequest request) {
		SyncReservationsResponse response = new SyncReservationsResponse();
		response.setReservations(service.findAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "approve_reservation_request")
	public ApproveReservationResponse approveReservation(@RequestPayload ApproveReservationRequest request) {
		ApproveReservationResponse response = new ApproveReservationResponse();
		
		if(service.checkIfReservationExists(request.getReservationId())) {
			service.approveReservation(request.getReservationId());
			response.setStauts(SOAPResponseStatus.SUCCESS);
			return response;
		}else {
			response.setStauts(SOAPResponseStatus.ERROR);
			return response;
		}
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirm_reservation_request")
	public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest request) {
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		
		if(service.checkIfReservationExists(request.getReservationId())) {
			service.confirmReservaiton(request.getReservationId());
			response.setStatus(SOAPResponseStatus.SUCCESS);
			return response;
		}else {
			response.setStatus(SOAPResponseStatus.ERROR);
			return response;
		}
	}
	
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "decline_reservation_request")
	public void declineReservation(@RequestPayload DeclineReservationRequest request) {
		if(service.checkIfReservationExists(request.getResId())){
			service.declineReservation(request.getResId());
		}
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agent_reservation_request")
	public AgentReservationResponse agentReservation(@RequestPayload AgentReservationRequest request) {
		AgentReservationResponse response = new AgentReservationResponse();
		Reservation res = service.saveReservation(request.getReservation());
		response.setReservationId(res.getId());
		return response;
	}
	
}
