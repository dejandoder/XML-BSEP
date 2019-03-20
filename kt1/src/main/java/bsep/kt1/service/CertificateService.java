package bsep.kt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsep.kt1.model.Certificate;
import bsep.kt1.repository.CertificateRepository;

@Service
public class CertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	public void addCertificate(Certificate certificate) {
		certificateRepository.save(certificate);
	}
	
	public List<CertificateDTO> getAll(){
		
	}
	
}
