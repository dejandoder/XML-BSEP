package bsep.kt1.keystores;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import bsep.kt1.data.IssuerData;

@Component
public class KeyStoreReader {
	//KeyStore je Java klasa za citanje specijalizovanih datoteka koje se koriste za cuvanje kljuceva
	//Tri tipa entiteta koji se obicno nalaze u ovakvim datotekama su:
	// - Sertifikati koji ukljucuju javni kljuc
	// - Privatni kljucevi
	// - Tajni kljucevi, koji se koriste u simetricnima siframa
	private KeyStore keyStore;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public KeyStoreReader() {
		try {
			keyStore = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
	/**
	 * Zadatak ove funkcije jeste da ucita podatke o izdavaocu i odgovarajuci privatni kljuc.
	 * Ovi podaci se mogu iskoristiti da se novi sertifikati izdaju.
	 * 
	 * @param keyStoreFile - datoteka odakle se citaju podaci
	 * @param alias - alias putem kog se identifikuje sertifikat izdavaoca
	 * @param password - lozinka koja je neophodna da se otvori key store
	 * @param keyPass - lozinka koja je neophodna da se izvuce privatni kljuc
	 * @return - podatke o izdavaocu i odgovarajuci privatni kljuc
	 */
	public IssuerData readIssuerFromStore(String keyStoreFile, String alias, char[] password, char[] keyPass) {
		try {
			//Datoteka se ucitava
			BufferedInputStream in = new BufferedInputStream(new FileInputStream( ResourceUtils.getFile("classpath:"+keyStoreFile)));
			keyStore.load(in, password);
			
			//System.out.println(ResourceUtils.getFile("classpath:"+keyStoreFile));
			
			//Iscitava se sertifikat koji ima dati alias
			Certificate cert = keyStore.getCertificate(alias);
			//Iscitava se privatni kljuc vezan za javni kljuc koji se nalazi na sertifikatu sa datim aliasom
			PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, keyPass);

			X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
			return new IssuerData(privKey, issuerName);
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ucitava sertifikat is KS fajla
	 */
    public Certificate readCertificate(String keyStoreFile, String keyStorePass, String alias) {
		try {
			//kreiramo instancu KeyStore
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			//ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(Paths.get(ResourceUtils.getFile("classpath:")+"\\..\\..\\src\\main\\resources").toRealPath().toString()+"\\"+keyStoreFile));
			ks.load(in, keyStorePass.toCharArray());
			
			if(ks.isKeyEntry(alias)) {
				Certificate cert = ks.getCertificate(alias);
				return cert;
			}
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
		return null;
	}
    public Enumeration<String> aliases(){
    	try {
			keyStore.aliases();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			logger.warn("I {}", e);
			e.printStackTrace();
		}
    	return null;
    }
	
	/**
	 * Ucitava privatni kljuc is KS fajla
	 */
	public PrivateKey readPrivateKey(String keyStoreFile, String keyStorePass, String alias, String pass) {
		try {
			//kreiramo instancu KeyStore
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			//ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile("classpath:"+keyStoreFile)));
			ks.load(in, keyStorePass.toCharArray());
			
			if(ks.isKeyEntry(alias)) {
				PrivateKey pk = (PrivateKey) ks.getKey(alias, pass.toCharArray());
				return pk;
			}
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
		return null;
	}
}
