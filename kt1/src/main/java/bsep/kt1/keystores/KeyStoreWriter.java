package bsep.kt1.keystores;

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
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


@Component
public class KeyStoreWriter {
	//KeyStore je Java klasa za citanje specijalizovanih datoteka koje se koriste za cuvanje kljuceva
	//Tri tipa entiteta koji se obicno nalaze u ovakvim datotekama su:
	// - Sertifikati koji ukljucuju javni kljuc
	// - Privatni kljucevi
	// - Tajni kljucevi, koji se koriste u simetricnima siframa
	private KeyStore keyStore;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public KeyStoreWriter() {
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
	
	
	public KeyStoreWriter(KeyStore keyStore) {
		super();
		this.keyStore = keyStore;
	}


	public void loadKeyStore(String fileName, char[] password) {
		try {
			if(fileName != null) {
				keyStore.load(new FileInputStream(Paths.get(ResourceUtils.getFile("classpath:")+"\\..\\..\\src\\main\\resources").toRealPath().toString()+"\\"+fileName), password);
			} else {
				//Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
				keyStore.load(null, password);
			}
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
	
	public void saveKeyStore(String fileName, char[] password) {
		try {
			keyStore.store(new FileOutputStream(Paths.get(ResourceUtils.getFile("classpath:")+"\\..\\..\\src\\main\\resources").toRealPath().toString()+"\\"+fileName), password);
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
	public void saveNewKeyStore(String fileName, char[] password) {
		try {
			keyStore.store(new FileOutputStream(Paths.get(ResourceUtils.getFile("classpath:")+"\\..\\..\\src\\main\\resources").toRealPath().toString()+"\\"+fileName), password);
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
	
	public String aliases(){
    	/*try {
		 return	keyStore.aliases().toString();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	return null;
    }
	
	public KeyStore getKeyStore() {
		return keyStore;
	}


	public void setKeyStore(KeyStore keyStore) {
		this.keyStore = keyStore;
	}


	public void writeCertificate(String alias, Certificate certificate) {
		try {		
			keyStore.setCertificateEntry(alias, certificate);
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
	
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		try {		
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
		} catch (KeyStoreException e) {
			logger.warn("I {}", e);
			e.printStackTrace();
		}
	}
}
