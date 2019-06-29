package xml_bsep.agent_app.soap_clients;

import java.util.List;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.AddImagesRequest;
import xml_bsep.agent_app.model.AddImagesResponse;
import xml_bsep.agent_app.model.AddNewAccomodationUnitRequest;
import xml_bsep.agent_app.model.AddNewAccomodationUnitResponse;
import xml_bsep.agent_app.model.AddPricePlanRequest;
import xml_bsep.agent_app.model.AddPricePlanResponse;
import xml_bsep.agent_app.model.Image;
import xml_bsep.agent_app.model.PricePlan;
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
	
	public AddImagesResponse addImages(List<Image> images) {
		AddImagesRequest request = new AddImagesRequest();
		request.setImages(images);
		
		AddImagesResponse response = (AddImagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		
		return response;
	}
	
	public AddPricePlanResponse addPricePlan(PricePlan pp) {
		AddPricePlanRequest request = new AddPricePlanRequest();
		request.setPricePlan(pp);
		
		AddPricePlanResponse response = (AddPricePlanResponse) getWebServiceTemplate()
				.marshalSendAndReceive(SERVICE_URI, request, new SoapActionCallback("/mySoapAction"));
		
		return response;
	}
}
