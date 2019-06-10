package xml_bsep.agent_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.AddNewAccomodationUnitResponse;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;


@RestController
@RequestMapping("/")
public class AccomodationUnitController {

	@Autowired
	AccomodationServiceSoapClient soapAccServiceClient;
	
	@PostMapping(value = "/addNewAccUnit")
	public ResponseEntity<AddNewAccomodationUnitResponse> addAccomodationUnit() {
		return new ResponseEntity<>(soapAccServiceClient.addNewAccomodationUnit(new AccomodationUnit()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/test")
	public String test() {
		return "radi";
	}
	
}
