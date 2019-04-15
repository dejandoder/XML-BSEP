package bsep.kt1.service;

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsep.kt1.dto.CommunicatonLinkDTO;
import bsep.kt1.keystores.KeyStoreReader;
import bsep.kt1.keystores.KeyStoreWriter;
import bsep.kt1.model.CommunicationLink;
import bsep.kt1.repository.CommunicationLinkRepository;

@Service
public class CommunicationLinkService {

	@Autowired
	CommunicationLinkRepository clRepository;
	
	@Autowired
	KeyStoreWriter writer;
	
	@Autowired
	KeyStoreReader reader;
	
	public void save(CommunicationLink cl) {
		clRepository.save(cl);
		bsep.kt1.model.Certificate cert1=cl.getMemberOne();
		bsep.kt1.model.Certificate cert2=cl.getMemberTwo();

		String serial1=Long.toString(cl.getMemberOne().getSerialNumber());
		String serial2=Long.toString(cl.getMemberTwo().getSerialNumber());
		
		
		
		Certificate certificate1= reader.readCertificate("ks/nonCA_KS.jks", "admin123", serial1);
		Certificate certificate2= reader.readCertificate("ks/nonCA_KS.jks", "admin123", serial2);
		
		KeyStoreWriter ksw1 = new KeyStoreWriter();
		String keyStoreFile1 = "ks/"+cert1.getCity()+"_"+cert1.getSoftwareModule()+"_TrustStore.jks";
		
		ksw1.loadKeyStore(keyStoreFile1, "admin123".toCharArray());
		ksw1.writeCertificate(serial2, certificate2);
		ksw1.saveKeyStore(keyStoreFile1, "admin123".toCharArray());

		KeyStoreWriter ksw2 = new KeyStoreWriter();
		String keyStoreFile2 = "ks/"+cert2.getCity()+"_"+cert2.getSoftwareModule()+"_TrustStore.jks";
		
		ksw2.loadKeyStore(keyStoreFile2, "admin123".toCharArray());
		ksw2.writeCertificate(serial1, certificate1);
		ksw2.saveKeyStore(keyStoreFile2, "admin123".toCharArray());
	}
	
	public List<CommunicatonLinkDTO> getAll(){
		List<CommunicationLink> cls = clRepository.findAll();
		List<CommunicatonLinkDTO> clsDTO = new ArrayList<CommunicatonLinkDTO>();
		
		for(CommunicationLink cl : cls) {
			clsDTO.add(new CommunicatonLinkDTO(cl));
		}
		
		return clsDTO;
	}
	
	public String checkCommunication(long s1, long s2) {
		List<CommunicationLink> cls = clRepository.checkCommunication(s1, s2);
		if(cls.isEmpty()) return "error";
		else return "ok";
	}
	
}

