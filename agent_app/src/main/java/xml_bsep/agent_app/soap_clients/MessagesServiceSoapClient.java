package xml_bsep.agent_app.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.SendMessageRequest;
import xml_bsep.agent_app.model.SendMessageResponse;
import xml_bsep.agent_app.model.SyncMessagesRequest;
import xml_bsep.agent_app.model.SyncMessagesResponse;



public class MessagesServiceSoapClient extends WebServiceGatewaySupport{

	private static final String SERVICE_URI = "http://localhost:8762/mess/soap";
	
	public SyncMessagesResponse syncMessages(SyncMessagesRequest request) {
		
		SyncMessagesResponse response = (SyncMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
	public SendMessageResponse sendMessage(SendMessageRequest request) {
		
		SendMessageResponse response = (SendMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
}
