package xml_bsep.rating_system.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.eureka.common.model.SyncRecensionsRequest;
import com.eureka.common.model.SyncRecensionsResponse;

import xml_bsep.rating_system.service.RecesnsionService;

@Endpoint
public class RatingEndpoint {

	private static final String NAMESPACE_URI = "http://www.ftn.uns.ac.rs/hotel-team1";
	
	@Autowired
	RecesnsionService service;
	
	@ResponsePayload
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sync_recensions_request")
	public SyncRecensionsResponse syncMessages(@RequestPayload SyncRecensionsRequest request) {
		SyncRecensionsResponse response = new SyncRecensionsResponse();
		response.setRecensions(service.findAll());
		return response;
	}
	
}
