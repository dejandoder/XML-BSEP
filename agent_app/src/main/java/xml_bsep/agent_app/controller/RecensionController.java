package xml_bsep.agent_app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import xml_bsep.agent_app.UrlUtils;
import xml_bsep.agent_app.dto.RecensionDTO;


@RestController
@RequestMapping("/")
public class RecensionController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(value = "/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionByAccUnit(@RequestBody long accId, HttpServletRequest requestAddr){
		String addr = requestAddr.getRemoteAddr().toString();
		HttpEntity<Long> request = new HttpEntity<Long>(accId);
		ResponseEntity<List<RecensionDTO>> response = restTemplate.
				exchange(UrlUtils.getRatingSystemUrl() + "/getRecensionsByAccUnit", HttpMethod.POST, request, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		logger.info("NP_EVENT POSN {} {}", addr, accId);
		return new ResponseEntity<List<RecensionDTO>>(response.getBody(),HttpStatus.OK);
	}
	
	
}
