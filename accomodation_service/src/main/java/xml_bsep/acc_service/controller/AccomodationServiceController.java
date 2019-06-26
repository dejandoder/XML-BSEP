package xml_bsep.acc_service.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eureka.common.model.AccomodationService;
import xml_bsep.acc_service.service.AccomodationServicesService;
import xml_bsep.acc_service.service.UserService;
import com.eureka.common.model.AccomodationUnit;
import xml_bsep.acc_service.service.AccomodationServicesService;
import xml_bsep.acc_service.service.AccomodationUnitService;

@RestController
@RequestMapping("/")
public class AccomodationServiceController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccomodationServicesService service;
	
	@Autowired
	UserService userService;
	
	AccomodationUnitService accUnitService;
		
	@PostMapping(value = "/admin/addAccService")
	public ResponseEntity<List<AccomodationService>> addNewAccService(@Valid @RequestBody AccomodationService accService){
		if(service.checkIfServicesExsist(accService.getName())) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		//sranje sa provjerama ako treba
		service.save(accService);
		List<AccomodationService> accServices = service.findAll();
		logger.info("NP_EVENT DSJ {} {}", userService.getCurrentUsername(), accService.getId());
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/getAllAccServices")
	public ResponseEntity<List<AccomodationService>> getAllAccService(HttpServletRequest request){
		String addr = request.getRemoteAddr().toString();
		//String addr = request.getHeader("X-FORWARDED-FOR");
		List<AccomodationService> accServices = service.findAll();
		logger.info("NP_EVENT PSJ {}",  addr);
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/removeAccService")
	public ResponseEntity<List<AccomodationService>> removeAccService(@Valid @RequestBody AccomodationService accService){
		//prvojeriti da li se moze obrisati accService, jer da neki hotel ima mozda
		for(AccomodationUnit accUnit : accUnitService.findAll()) {
			if(accUnit.hasService(accService)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		List<AccomodationService> accServices = service.delete(accService.getId());
		logger.info("NP_EVENT BSJ {} {}", userService.getCurrentUsername(), accService.getId());
		return new ResponseEntity<>(accServices, HttpStatus.OK);
	}
}
