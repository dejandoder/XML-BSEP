package bsep.kt1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsep.kt1.data.IssuerData;
import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.keystores.KeyStoreReader;
import bsep.kt1.keystores.KeyStoreWriter;
import bsep.kt1.model.Certificate;
import bsep.kt1.repository.CertificateRepository;

@Service
public class CertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	KeyStoreWriter writer;
	
	@Autowired
	KeyStoreReader reader;
	
	
	
	public void addCertificate(Certificate certificate, String caSerialNumber) {
		certificateRepository.save(certificate);
		
	}
	
	public List<CertificateDTO> getAll(){
		List<Certificate> certificates = certificateRepository.findAll();
		List<CertificateDTO> certificatesDTO = new ArrayList<CertificateDTO>();
		
		for(Certificate certificate : certificates) {
			certificatesDTO.add(new CertificateDTO(certificate));
		}
		
		return certificatesDTO;
	}
	
	public List<CertificateDTO> getAllCA(){
		List<Certificate> certificates = certificateRepository.getAllCA();
		List<CertificateDTO> certificatesDTO = new ArrayList<CertificateDTO>();
		
		for(Certificate certificate : certificates) {
			certificatesDTO.add(new CertificateDTO(certificate));
		}
		
		return certificatesDTO;
	}
	
	public List<CertificateDTO> getAllNonCA(){
		List<Certificate> certificates = certificateRepository.getAllNonCA();
		List<CertificateDTO> certificatesDTO = new ArrayList<CertificateDTO>();
		
		for(Certificate certificate : certificates) {
			certificatesDTO.add(new CertificateDTO(certificate));
		}
		
		return certificatesDTO;
	}
	
	public List<CertificateDTO> revokeCertificate(long serialNumber) {
		certificateRepository.revokeCertificat(serialNumber);
		
		List<Certificate> certificates = certificateRepository.findAll();
		List<CertificateDTO> certificatesDTO = new ArrayList<CertificateDTO>();
		
		for(Certificate certificate : certificates) {
			certificatesDTO.add(new CertificateDTO(certificate));
		}
		
		return certificatesDTO;
	}
	
}
