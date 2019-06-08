package xml_bsep.acc_service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.eureka.common.model.AddNewAccomodationUnitRequest;
import com.eureka.common.model.AddNewAccomodationUnitResponse;
import com.eureka.common.model.SOAPResponseStatus;

import xml_bsep.acc_service.service.AccomodationUnitService;

@Endpoint
public class AccomodationUnitEndpoint {
	
	@Autowired
	AccomodationUnitService service;
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "add_new_accomodation_unit_request")
	public AddNewAccomodationUnitResponse addAccomodationUnit(@RequestPayload AddNewAccomodationUnitRequest request) {
		AddNewAccomodationUnitResponse response = new AddNewAccomodationUnitResponse();
		return response;
	}
	
	
	
}
