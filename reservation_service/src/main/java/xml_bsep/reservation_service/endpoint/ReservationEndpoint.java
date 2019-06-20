package xml_bsep.reservation_service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import xml_bsep.reservation_service.service.ReservationService;

import com.eureka.common.model.AgentReservationRequest;
import com.eureka.common.model.AgentReservationResponse;
import com.eureka.common.model.ApproveReservationRequest;
import com.eureka.common.model.ApproveReservationResponse;
import com.eureka.common.model.ConfirmReservationRequest;
import com.eureka.common.model.ConfirmReservationResponse;
import com.eureka.common.model.SyncReservationsRequest;
import com.eureka.common.model.SyncReservationsResponse;

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
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "confirm_reservation_request")
	public ConfirmReservationResponse confirmReservation(@RequestPayload ConfirmReservationRequest request) {
		ConfirmReservationResponse response = new ConfirmReservationResponse();
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "agent_reservation_request")
	public AgentReservationResponse agentReservation(@RequestPayload AgentReservationRequest request) {
		AgentReservationResponse response = new AgentReservationResponse();
		return response;
	}
	
}
