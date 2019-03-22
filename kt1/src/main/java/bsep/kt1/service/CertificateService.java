package bsep.kt1.service;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsep.kt1.data.IssuerData;
import bsep.kt1.data.SubjectData;

import bsep.kt1.dto.CertificateDTO;
import bsep.kt1.keystores.KeyStoreReader;
import bsep.kt1.keystores.KeyStoreWriter;
import bsep.kt1.model.Certificate;
import bsep.kt1.repository.CertificateRepository;
import bsep.kt1.certificates.*;



@Service
public class CertificateService {

	@Autowired
	CertificateRepository certificateRepository;
	
	@Autowired
	KeyStoreWriter writer;
	
	@Autowired
	KeyStoreReader reader;
	
	
	
	public void addCertificate(Certificate certificate, long caSerialNumber) {
		
		Certificate savedCertificate = certificateRepository.save(certificate);
		
		certificate.setCaSerialNumber(caSerialNumber);
		
		IssuerData issuerData = getIssuer(Long.toString(caSerialNumber), "ks/caKS.jks");
		
		KeyPair subjectKey = generateKeyPair();
		
		SubjectData subjectData = generateSubjectData(savedCertificate,subjectKey.getPublic());
		
		CertificateGenerator cg = new CertificateGenerator();
		
		X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
		
		String keyStoreFile = "";
		
		if(certificate.isCa()) keyStoreFile = "ks/caKS.jks";
		else if(certificate.getCity().toLowerCase().equals("boston")) keyStoreFile = "ks/bostonKS.jks";
		else if(certificate.getCity().toLowerCase().equals("hongkong")) keyStoreFile = "ks/hongkongKS.jks";
		else if(certificate.getCity().toLowerCase().equals("london")) keyStoreFile = "ks/londonKS.jks";
		
		KeyStoreWriter ksw = new KeyStoreWriter();
		ksw.loadKeyStore(keyStoreFile, "admin123".toCharArray());
		ksw.write(subjectData.getSerialNumber(), subjectKey.getPrivate(), "admin123".toCharArray(), cert);
		ksw.saveKeyStore(keyStoreFile, "admin123".toCharArray());
		
		
		//initilizeKS();
		
		
	}
	public Certificate getById(long id) {
		return certificateRepository.findOneBySerialNumber(id);
		
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
	
	private IssuerData getIssuer(String serialNumber, String fileName) {
		return reader.readIssuerFromStore(fileName, serialNumber, "admin123".toCharArray(), "admin123".toCharArray());
	}

	private KeyPair generateKeyPair() {
        try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(2048, random);
			return keyGen.generateKeyPair();
			
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	private bsep.kt1.data.SubjectData generateSubjectData(Certificate certificate, PublicKey pk) {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			Date startDate = certificate.getFromDate();
			Date endDate = certificate.getFromDate();
			
			//Serijski broj sertifikata
			String sn = Long.toString(certificate.getSerialNumber());
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, "Admin Admin");
		    builder.addRDN(BCStyle.SURNAME, "Admin");
		    builder.addRDN(BCStyle.GIVENNAME, "Admin");
		    builder.addRDN(BCStyle.L , certificate.getCity());
		    builder.addRDN(BCStyle.OU , certificate.getSoftwareModule());
		    builder.addRDN(BCStyle.E, "admin@uns.ac.rs");
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, "123456");
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new bsep.kt1.data.SubjectData(pk, builder.build(), sn, startDate, endDate);
	}


	private void initilizeKS() {
		
		KeyStoreWriter ksw = new KeyStoreWriter();
		ksw.loadKeyStore(null, "admin123".toCharArray());
		
		ksw.saveKeyStore("ks/bostonKS.jks", "admin123".toCharArray());
		
	    ksw = new KeyStoreWriter();
		ksw.loadKeyStore(null, "admin123".toCharArray());
		
		ksw.saveKeyStore("ks/londonKS.jks", "admin123".toCharArray());
		
		ksw = new KeyStoreWriter();
		ksw.loadKeyStore(null, "admin123".toCharArray());
		
		ksw.saveKeyStore("ks/hongkongKS.jks", "admin123".toCharArray());
		
	}

}

