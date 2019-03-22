package bsep.kt1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.model.Certificate;
import bsep.kt1.service.CertificateService;

@RestController
@RequestMapping(value="/certificate")
public class CertificateController {
	
	@Autowired
	CertificateService service;
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json", value="/{caSerialNumber}")
	public void addCertificate(@RequestBody Certificate certificate, @PathVariable String caSerialNumber){
		service.addCertificate(certificate, caSerialNumber);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAll")
	public ResponseEntity<List<CertificateDTO>>getAllCertificates(){
		List<CertificateDTO> certificates = service.getAll();
		//aaaaaaaaaaaaaaaaa
		return new ResponseEntity<>(certificates, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value = "/getAllCA")
	public ResponseEntity<List<CertificateDTO>>getAllCACertificates(){
		List<CertificateDTO> certificates = service.getAllCA();
		return new ResponseEntity<>(certificates, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.GET, value = "/getAllNonCA")
	public ResponseEntity<List<CertificateDTO>>getAllNonCACertificates(){
		List<CertificateDTO> certificates = service.getAllNonCA();
		return new ResponseEntity<>(certificates, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/revokeCertificat/{serialNumber}")
	public ResponseEntity<List<CertificateDTO>> revokeCertificates(@PathVariable String serialNumber){
		List<CertificateDTO> certificates = service.revokeCertificate(serialNumber);
		return new ResponseEntity<>(certificates, HttpStatus.OK);
	}
	
	
}
