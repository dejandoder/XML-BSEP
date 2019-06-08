package xml_bsep.acc_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class AccomodationUnitController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/test")
	public String test(){
		//String s = restTemplate.getForObject("http://db-service/", String.class);
		return "acc servis radi";
	}
}
