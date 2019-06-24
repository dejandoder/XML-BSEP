package xml_bsep.agent_app.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import xml_bsep.agent_app.UrlUtils;
import xml_bsep.agent_app.dto.RecensionDTO;


@RestController
@RequestMapping("/")
@Validated
public class RecensionController {

	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping(value = "/getRecensionsByAccUnit")
	public ResponseEntity<List<RecensionDTO>> getRecensionByAccUnit(@RequestBody @Min(1) long accId){
		
		HttpEntity<Long> request = new HttpEntity<Long>(accId);
		ResponseEntity<List<RecensionDTO>> response = restTemplate.
				exchange(UrlUtils.getRatingSystemUrl() + "/all/getRecensionsByAccUnit", HttpMethod.POST, request, new ParameterizedTypeReference<List<RecensionDTO>>(){});
		
		return new ResponseEntity<List<RecensionDTO>>(response.getBody(),HttpStatus.OK);
	}
	
	
}
