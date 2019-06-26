package xml_bsep.agent_app.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.AgentReservationRequest;
import xml_bsep.agent_app.model.AgentReservationResponse;
import xml_bsep.agent_app.model.ApproveReservationRequest;
import xml_bsep.agent_app.model.ApproveReservationResponse;
import xml_bsep.agent_app.model.ConfirmReservationRequest;
import xml_bsep.agent_app.model.ConfirmReservationResponse;
import xml_bsep.agent_app.model.DeclineReservationRequest;
import xml_bsep.agent_app.model.SyncReservationsRequest;
import xml_bsep.agent_app.model.SyncReservationsResponse;

public class ReservationServiceSoapClient extends WebServiceGatewaySupport {

	private static final String SERVICE_URI = "https://localhost:8762/res/soap";
	
	public SyncReservationsResponse syncReservation() {
	
		SyncReservationsRequest request= new SyncReservationsRequest();
		
		SyncReservationsResponse response = (SyncReservationsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
	public ApproveReservationResponse approveReservation(ApproveReservationRequest request) {
		
		ApproveReservationResponse response = (ApproveReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
	public ConfirmReservationResponse confirmReservation(ConfirmReservationRequest request) {
		
		ConfirmReservationResponse response = (ConfirmReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
	public AgentReservationResponse agentReservation(AgentReservationRequest request) {
		
		AgentReservationResponse response = (AgentReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
	public void declineReservation(DeclineReservationRequest request) {	
		getWebServiceTemplate().marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
	}
}
