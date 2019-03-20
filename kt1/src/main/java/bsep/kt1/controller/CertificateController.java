package bsep.kt1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.model.Certificate;

@RestController
@RequestMapping(value="/certificate")
public class CertificateController {
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public void addCertificate(@RequestBody Certificate certificate){
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAll")
	public ResponseEntity<List<CertificateDTO>>getAllCertificates(){
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAllCA")
	public ResponseEntity<List<CertificateDTO>>getAllCACertificates(){
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value = "/getAllNonCA")
	public ResponseEntity<List<CertificateDTO>>getAllNonCACertificates(){
		return null;
	}
	
}
