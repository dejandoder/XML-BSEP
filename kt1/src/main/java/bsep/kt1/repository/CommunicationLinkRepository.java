package bsep.kt1.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bsep.kt1.model.Certificate;
import bsep.kt1.model.CommunicationLink;

public interface CommunicationLinkRepository extends JpaRepository<CommunicationLink, Long> {
	
	CommunicationLink save(CommunicationLink cl);
	
	List<CommunicationLink> findAll();

}
