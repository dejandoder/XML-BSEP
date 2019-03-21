package bsep.kt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.dto.CommunicatonLinkDTO;
import bsep.kt1.service.CommunicationLinkService;

@RestController
@RequestMapping(value="/cl")
public class CommunicationLinkController {

	@Autowired
	CommunicationLinkService service;
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAllCl")
	public ResponseEntity<List<CommunicatonLinkDTO>>getAllCl(){
		List<CommunicatonLinkDTO> cls = service.getAll();
		return new ResponseEntity<>(cls, HttpStatus.OK);
	}
	
}
