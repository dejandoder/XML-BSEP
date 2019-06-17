package xml_bsep.agent_app.soap_clients;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.AddNewAccomodationUnitRequest;
import xml_bsep.agent_app.model.AddNewAccomodationUnitResponse;
import xml_bsep.agent_app.model.SyncAccServicesRequest;
import xml_bsep.agent_app.model.SyncAccServicesResponse;
import xml_bsep.agent_app.model.SyncAccomodationTypeResponse;
import xml_bsep.agent_app.model.SyncAccomodationTypesRequest;


public class AccomodationServiceSoapClient extends WebServiceGatewaySupport {
	
	private static final String SERVICE_URI = "http://localhost:8762/acc/soap";

	public AddNewAccomodationUnitResponse addNewAccomodationUnit(AccomodationUnit accUnit) {
		
		AddNewAccomodationUnitRequest request = new AddNewAccomodationUnitRequest();
		request.setAccUnit(accUnit);
		
		AddNewAccomodationUnitResponse response = (AddNewAccomodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		
		return response;
	}
	
	public SyncAccServicesResponse syncAccService() {
		SyncAccServicesRequest request = new SyncAccServicesRequest();
		SyncAccServicesResponse response = (SyncAccServicesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		return response;
	}
	
	public SyncAccomodationTypeResponse syncAccomodationTypes() {
		SyncAccomodationTypesRequest request = new SyncAccomodationTypesRequest();
		SyncAccomodationTypeResponse response = (SyncAccomodationTypeResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		return response;
	}
}
