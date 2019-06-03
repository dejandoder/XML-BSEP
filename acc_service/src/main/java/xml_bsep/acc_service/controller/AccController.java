package xml_bsep.acc_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class AccController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String test(){
		//String s = restTemplate.getForObject("http://db-service/", String.class);
		return "kurcevina";
	}
}
