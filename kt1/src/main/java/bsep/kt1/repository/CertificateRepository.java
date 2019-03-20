package bsep.kt1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import bsep.kt1.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	Certificate save(Certificate certificate);
	
	Certificate findOneBySerialNumber(long serialNumbe);
	
	List<Certificate> findAll();
	
	@Query("select certificate from Certificate certificate where certificate.ca = true")
	List<Certificate> getAllCA();

	@Query("select certificate from Certificate certificate where certificate.ca = false")
	List<Certificate> getAllNonCA();
}

