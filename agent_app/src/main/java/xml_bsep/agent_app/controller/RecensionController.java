package xml_bsep.agent_app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.dto.RecensionDTO;
import xml_bsep.agent_app.model.Recension;
import xml_bsep.agent_app.service.RecensionsService;


@RestController
@RequestMapping("/")
@Validated
public class RecensionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	RecensionsService service;
	@PostMapping(value = "/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionByAccUnit(@RequestBody @Min(1) long accId, HttpServletRequest requestAddr){
		String addr = requestAddr.getRemoteAddr().toString();
		
		List<Recension> recensions = service.getRecensionsByAccUnit(accId);
		
		List<RecensionDTO> recensionsDTO = new ArrayList<>();
		
		for (Recension recension : recensions) {
			recensionsDTO.add(new RecensionDTO(recension));
		}
				
		logger.info("NP_EVENT POSN {} {}", addr, accId);
		return new ResponseEntity<List<RecensionDTO>>(recensionsDTO,HttpStatus.OK);
	}
	
	
}
