package xml_bsep.acc_service.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.AccomodationUnit;

import xml_bsep.acc_service.dto.AccomodationUnitDTO;
import xml_bsep.acc_service.dto.SearchDTO;
import xml_bsep.acc_service.service.AccomodationUnitService;
import xml_bsep.acc_service.service.UserService;

@RestController
@RequestMapping("/")
public class AccomodationUnitController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccomodationUnitService accService;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/search")
	public ResponseEntity<List<AccomodationUnitDTO>> search(@RequestBody SearchDTO searchDTO){
		List<AccomodationUnitDTO> retVal = accService.search(searchDTO);
		logger.info("NP_EVENT PSO {}", userService.getCurrentUsername());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAccUnit")
	public ResponseEntity<AccomodationUnit> getAccUnit(@RequestBody long accId, HttpServletRequest request){
		AccomodationUnit accUnit = accService.findOne(accId);
		String addr = request.getRemoteAddr().toString();
		logger.info("NP_EVENT PSJ {}", addr); 				//////////
		return new ResponseEntity<>(accUnit, HttpStatus.OK);
	}
}
