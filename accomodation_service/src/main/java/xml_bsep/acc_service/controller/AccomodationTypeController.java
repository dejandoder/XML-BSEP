package xml_bsep.acc_service.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eureka.common.model.AccomodationType;
import xml_bsep.acc_service.service.AccomodationTypeService;
import xml_bsep.acc_service.service.UserService;

@RestController
@RequestMapping("/")
public class AccomodationTypeController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccomodationTypeService service;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/admin/addNewAccType", consumes = "application/json")
	public ResponseEntity<List<AccomodationType>> addNewAccType(@RequestBody AccomodationType accType){
		if(service.checkIfTypeExsists(accType.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//odje bi sada trebale neke sql parsiarnje provjere i te pizdarije
		service.save(accType);
		List<AccomodationType> accTypes = service.findAll();
		logger.info("NP_EVENT DTS {} {}", userService.getCurrentUsername(), accType.getId());
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/removeAccType")
	public ResponseEntity<List<AccomodationType>> removeAccType(@RequestBody AccomodationType accType){
		//treba uraditi provjeru da li ima smjestaja sa zadatim tipom
		//return new ResponseEntity<>(HttpStatus.BAD_REQUEST);	
		List<AccomodationType> accTypes = service.delete(accType.getId());
		logger.info("NP_EVENT BTS {} {}", userService.getCurrentUsername(), accType.getId());
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/getAllAccTypes")
	public ResponseEntity<List<AccomodationType>> getAllAccTypes(HttpServletRequest request){
		List<AccomodationType> accTypes = service.findAll();
		String addr = request.getRemoteAddr().toString();
		logger.info("NP_EVENT PTS {}", addr);
		return new ResponseEntity<>(accTypes, HttpStatus.OK);
	}
	
}