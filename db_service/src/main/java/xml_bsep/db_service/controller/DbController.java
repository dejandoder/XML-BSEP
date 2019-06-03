package xml_bsep.db_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DbController {

	@GetMapping("/agent")
	public String test(){
		return "radi agent";
	}
	
	@GetMapping("/admin")
	public String test3(){
		return "radi admin";
	}
	
}
