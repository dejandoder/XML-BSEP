package bsep.kt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import bsep.kt1.dto.CommunicatonLinkDTO;
import bsep.kt1.model.Certificate;
import bsep.kt1.model.CommunicationLink;
import bsep.kt1.service.CertificateService;
import bsep.kt1.service.CommunicationLinkService;

@RestController
@RequestMapping(value="/cl")
public class CommunicationLinkController {

	@Autowired
	CommunicationLinkService service;
	@Autowired
	CertificateService certificateService;
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAllCl")
	public ResponseEntity<List<CommunicatonLinkDTO>>getAllCl(){
		List<CommunicatonLinkDTO> cls = service.getAll();
		return new ResponseEntity<>(cls, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/createLink/{id}/{id2}")
	public ResponseEntity<List<CommunicatonLinkDTO>> createLink(@PathVariable long id, @PathVariable long id2){
		Certificate sertifikat1= certificateService.getById(id);
		Certificate sertifikat2= certificateService.getById(id2);
		
		CommunicationLink link= new CommunicationLink(sertifikat1,sertifikat2);
		service.save(link);
		
		List<CommunicatonLinkDTO> cls = service.getAll();
		return new ResponseEntity<>(cls, HttpStatus.OK);
	}
}
