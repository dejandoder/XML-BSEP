package xml_bsep.acc_service.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.eureka.common.model.AccomodationService;
import com.eureka.common.model.AccomodationType;
import com.eureka.common.model.AccomodationUnit;
import com.eureka.common.model.AddImagesRequest;
import com.eureka.common.model.AddImagesResponse;
import com.eureka.common.model.AddNewAccomodationUnitRequest;
import com.eureka.common.model.AddNewAccomodationUnitResponse;
import com.eureka.common.model.AddPricePlanRequest;
import com.eureka.common.model.AddPricePlanResponse;
import com.eureka.common.model.Image;
import com.eureka.common.model.SyncAccServicesRequest;
import com.eureka.common.model.SyncAccServicesResponse;
import com.eureka.common.model.SyncAccomodationTypeResponse;
import com.eureka.common.model.SyncAccomodationTypesRequest;

import xml_bsep.acc_service.service.AccomodationServicesService;
import xml_bsep.acc_service.service.AccomodationTypeService;
import xml_bsep.acc_service.service.AccomodationUnitService;
import xml_bsep.acc_service.service.ImageService;
import xml_bsep.acc_service.service.PricePlaneService;


@Endpoint
public class AccomodationUnitEndpoint {
	
	@Autowired
	AccomodationUnitService service;
	
	@Autowired
	AccomodationTypeService typesService;
	
	@Autowired
	AccomodationServicesService servicesService;
	
	@Autowired
	ImageService imageService;
	
	@Autowired
	PricePlaneService ppService;
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "add_new_accomodation_unit_request")
	public AddNewAccomodationUnitResponse addAccomodationUnit(@RequestPayload AddNewAccomodationUnitRequest request) {
		
		AccomodationUnit accUnit = request.getAccUnit();
		service.save(accUnit);
		
		AddNewAccomodationUnitResponse response = new AddNewAccomodationUnitResponse();
		response.setAccUnitId(accUnit.getId());
		
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "sync_acc_types_request")
	public SyncAccomodationTypeResponse syncAccomodationTypes(@RequestPayload SyncAccomodationTypesRequest request) {
		SyncAccomodationTypeResponse response = new SyncAccomodationTypeResponse();
		response.setTypes((ArrayList<AccomodationType>) typesService.findAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "sync_acc_services_request")
	public SyncAccServicesResponse syncAccServices(@RequestPayload SyncAccServicesRequest request) {
		SyncAccServicesResponse response = new SyncAccServicesResponse();
		response.setService((ArrayList<AccomodationService>) servicesService.findAll());
		return response;
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "add_images_request")
	public AddImagesResponse addImages(@RequestPayload AddImagesRequest request) {
		List<Image> images = request.getImages();
		imageService.saveAll(images);
		return new AddImagesResponse();
	}
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "add_price_plan_request")
	public AddPricePlanResponse addPricePlan(@RequestPayload AddPricePlanRequest request) {
		ppService.save(request.getPricePlan());
		return new AddPricePlanResponse();
	}
	
	
}
