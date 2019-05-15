package bsep.kt1.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.dto.CommunicatonLinkDTO;
import bsep.kt1.model.Certificate;
import bsep.kt1.model.CommunicationLink;
import bsep.kt1.service.CertificateService;
import bsep.kt1.service.CommunicationLinkService;
import bsep.kt1.service.UserService;
import bsep.kt1.utils.LoggingUtils;

@RestController
@RequestMapping(value="/cl")
public class CommunicationLinkController {

	@Autowired
	CommunicationLinkService service;
	@Autowired
	CertificateService certificateService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value = "/getAllCl")
	public ResponseEntity<List<CommunicatonLinkDTO>>getAllCl(){
		List<CommunicatonLinkDTO> cls = service.getAll();
		logger.info("NP_EVENT PKV {}",userService.getCurrentUser().getEmail());
		return new ResponseEntity<>(cls, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value = "/createLink/{id}/{id2}")
	public ResponseEntity<List<CommunicatonLinkDTO>> createLink(@PathVariable long id, @PathVariable long id2){
		Certificate sertifikat1= certificateService.getById(id);
		Certificate sertifikat2= certificateService.getById(id2);
		
		CommunicationLink link= new CommunicationLink(sertifikat1,sertifikat2);
		service.save(link);
	
		logger.info("NP_EVENT KKV {} {} {} ",userService.getCurrentUser().getEmail(), id, id2);
		
		
		List<CommunicatonLinkDTO> cls = service.getAll();
		return new ResponseEntity<>(cls, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method=RequestMethod.GET, value = "/checkCommunication/{serialNumberOne}/{serialNumberTwo}")
	public ResponseEntity<String> checkCommunication(@PathVariable long serialNumberOne, @PathVariable long serialNumberTwo){
		String retVal = service.checkCommunication(serialNumberOne, serialNumberTwo);
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
}
