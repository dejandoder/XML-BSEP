package xml_bsep.agent_app.controller;

import java.util.ArrayList;
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

import xml_bsep.agent_app.dto.AccomodationUnitDTO;
import xml_bsep.agent_app.model.AccomodationService;
import xml_bsep.agent_app.model.AccomodationType;
import xml_bsep.agent_app.model.AccomodationUnit;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.service.AccomodationServicesService;
import xml_bsep.agent_app.service.AccomodationTypeService;
import xml_bsep.agent_app.service.AccomodationUnitService;
import xml_bsep.agent_app.service.ImageService;
import xml_bsep.agent_app.service.UserService;
import xml_bsep.agent_app.soap_clients.AccomodationServiceSoapClient;

import org.springframework.util.Base64Utils;

@RestController
@RequestMapping("/")
public class AccomodationUnitController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccomodationServiceSoapClient soapAccServiceClient;
	
	@Autowired
	AccomodationServicesService servicesService;
	
	@Autowired
	AccomodationTypeService typeService;
	
	@Autowired
	AccomodationUnitService accUnitService;
	
	@Autowired
	ImageService imageService;
	
	@PostMapping(value = "/addNewAccUnit")
	public ResponseEntity<AccomodationUnitDTO> addAccomodationUnit(@RequestBody AccomodationUnit newAccUnit) {
		User user = userService.getCurrentUser();
		newAccUnit.setAgent(user);
		AccomodationUnit savedAccUnit = accUnitService.save(newAccUnit);
		logger.info("NP_EVENT DSJ {} {}", userService.getCurrentUserName(), newAccUnit.getId());
		return new ResponseEntity<>(new AccomodationUnitDTO(savedAccUnit), HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAccTypes")
	public ResponseEntity<List<AccomodationType>> getAllAccomodationTypes(HttpServletRequest request){
		List<AccomodationType> types = typeService.findAll();
		String addr = request.getRemoteAddr().toString();
		logger.info("NP_EVENT PTS {}", addr);
		return new ResponseEntity<>(types, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAccServices")
	public ResponseEntity<List<AccomodationService>> getAllAccomodationServices(HttpServletRequest request){
		List<AccomodationService> services = servicesService.findAll();
		String addr = request.getRemoteAddr().toString();
		logger.info("NP_EVENT PSJ {}",  addr);
		return new ResponseEntity<>(services, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getAllAccUnitByAgent")
	public ResponseEntity<List<AccomodationUnitDTO>> getAllAccUnitsByAgent(){
		User agent = userService.getCurrentUser();
		List<AccomodationUnitDTO> retVal = accUnitService.getAccUnitsByAgent(agent);
		logger.info("NP_EVENT PSJA {}",  userService.getCurrentUser());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<List<AccomodationUnitDTO>> test(){
		List<AccomodationUnitDTO> retVal = accUnitService.getAll();
		int pom = 1;
		for (AccomodationUnitDTO accomodationUnitDTO : retVal) {
			accomodationUnitDTO.setRating(pom);
			accomodationUnitDTO.setDistance(pom*100 - 98);
			pom++;
			accomodationUnitDTO.setPrice(pom);
			ArrayList<byte[]> binaryImages= imageService.getImagesByAccomodationUnit(accomodationUnitDTO.getId());
			for (byte[] bs : binaryImages) {
				accomodationUnitDTO.addImage(Base64Utils.encodeToString(bs));
			}
		}logger.info("NP_EVENT PSJA {}",  userService.getCurrentUser());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
}
