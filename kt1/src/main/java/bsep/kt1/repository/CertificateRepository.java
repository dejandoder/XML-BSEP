package bsep.kt1.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bsep.kt1.model.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

	@SuppressWarnings("unchecked")
	Certificate save(Certificate certificate);
	
	Certificate findOneBySerialNumber(long serialNumbe);
	
	List<Certificate> findAll();
	
	@Query("select certificate from Certificate certificate where certificate.ca = true")
	List<Certificate> getAllCA();

	@Query("select certificate from Certificate certificate where certificate.ca = false")
	List<Certificate> getAllNonCA();
	
	@Transactional
	@Modifying
	@Query("update Certificate certificate set certificate.revoked = true where certificate.serialNumber = :serialNumber")
	public void revokeCertificat(@Param("serialNumber") long serialNumber);
}

