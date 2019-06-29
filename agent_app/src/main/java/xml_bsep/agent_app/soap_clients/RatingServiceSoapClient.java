package xml_bsep.agent_app.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.SyncRecensionsRequest;
import xml_bsep.agent_app.model.SyncRecensionsResponse;

public class RatingServiceSoapClient extends WebServiceGatewaySupport{

private static final String SERVICE_URI = "http://localhost:8762/rat/soap";
	
	public SyncRecensionsResponse syncRecensions() {
	
		SyncRecensionsRequest request= new SyncRecensionsRequest();
		
		SyncRecensionsResponse response = (SyncRecensionsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback(""));
		
		return response;
	}
	
}
