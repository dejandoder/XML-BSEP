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
	
	
	
	public void addCertificate(Certificate certificate, String caSerialNumber) {
		certificateRepository.save(certificate);
		IssuerData issuer = getIssuer(caSerialNumber, "ks/adminKS.jks");
		//testIt();
		
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
	
	public List<CertificateDTO> revokeCertificate(String serialNumber) {
		certificateRepository.revokeCertificat(serialNumber);
		
		List<Certificate> certificates = certificateRepository.findAll();
		List<CertificateDTO> certificatesDTO = new ArrayList<CertificateDTO>();
		
		for(Certificate certificate : certificates) {
			certificatesDTO.add(new CertificateDTO(certificate));
		}
		
		return certificatesDTO;
	}
	
	private IssuerData getIssuer(String serialNumber, String fileName) {
		reader.readCertificate("C:\\Users\\miljan\\Desktop\\XML-BSEP\\kt1\\src\\main\\resources\\ks\\adminKS.jks", "admin123", "1");
		//return null;
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
	

	private static PublicKey pk;
	
	public void testIt() {
		try {
			KeyPair keyPairIssuer = generateKeyPair();
			IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate());
			
			//*****
			pk = keyPairIssuer.getPublic();
		    
			SubjectData subjectData = generateSubjectData();
			
			//Generise se sertifikat za subjekta, potpisan od strane issuer-a
			CertificateGenerator cg = new CertificateGenerator();
			
			X509Certificate cert = cg.generateCertificate(subjectData, issuerData);
			
		
			/************* MOJA GOVNA *****/
			KeyStoreWriter ksw = new KeyStoreWriter();
			ksw.loadKeyStore(null, "admin123".toCharArray());
			
			ksw.saveKeyStore("C:\\Users\\miljan\\Desktop\\XML-BSEP\\kt1\\src\\main\\resources\\ks\\adminKS.jks", "admin123".toCharArray());
			ksw.write(subjectData.getSerialNumber(), keyPairIssuer.getPrivate(), "admin123".toCharArray(), cert);
			ksw.saveKeyStore("C:\\Users\\miljan\\Desktop\\XML-BSEP\\kt1\\src\\main\\resources\\ks\\adminKS.jks", "admin123".toCharArray());
			
			//Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
			cert.verify(keyPairIssuer.getPublic());
			//System.out.println("\nValidacija uspesna :)");
			
			//Ovde se desava exception, jer se validacija vrsi putem drugog kljuca
			KeyPair anotherPair = generateKeyPair();
			cert.verify(anotherPair.getPublic());
		}catch(NoSuchProviderException | InvalidKeyException | CertificateException | NoSuchAlgorithmException | SignatureException e) {
			/*/kurciaaaaa*/
		}
	}
	
	private IssuerData generateIssuerData(PrivateKey issuerKey) {
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		 builder.addRDN(BCStyle.CN, "Admin Admin");
		 builder.addRDN(BCStyle.SURNAME, "Admin");
		 builder.addRDN(BCStyle.GIVENNAME, "Admin");
		 builder.addRDN(BCStyle.C, "RS");
		 builder.addRDN(BCStyle.E, "admin@uns.ac.rs");
	     //UID (USER ID) je ID korisnika
		 builder.addRDN(BCStyle.UID, "123456");

		//Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
	    // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
	    // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
		return new IssuerData(issuerKey, builder.build());
	}

	private bsep.kt1.data.SubjectData generateSubjectData() {
		try {
			KeyPair keyPairSubject = generateKeyPair();
			
			//Datumi od kad do kad vazi sertifikat
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = iso8601Formater.parse("2018-12-31");
			Date endDate = iso8601Formater.parse("2022-12-31");
			
			//Serijski broj sertifikata
			String sn="1";
			//klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, "Admin Admin");
		    builder.addRDN(BCStyle.SURNAME, "Admin");
		    builder.addRDN(BCStyle.GIVENNAME, "Admin");
		    builder.addRDN(BCStyle.C, "RS");
		    builder.addRDN(BCStyle.E, "admin@uns.ac.rs");
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, "123456");
		    
		    //Kreiraju se podaci za sertifikat, sto ukljucuje:
		    // - javni kljuc koji se vezuje za sertifikat
		    // - podatke o vlasniku
		    // - serijski broj sertifikata
		    // - od kada do kada vazi sertifikat
		    return new bsep.kt1.data.SubjectData(pk, builder.build(), sn, startDate, endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}

