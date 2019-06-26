package xml_bsep.agent_app.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.SyncUsersRequest;
import xml_bsep.agent_app.model.SyncUsersResponse;

public class AuthServiceSoapClient extends WebServiceGatewaySupport{

	private static final String SERVICE_URI = "https://localhost:8762/auth/soap";
	
	public SyncUsersResponse syncUsersRequest() {
		SyncUsersRequest request = new SyncUsersRequest();
		
		SyncUsersResponse response = (SyncUsersResponse) getWebServiceTemplate().marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		
		return response;
	}
	
}
