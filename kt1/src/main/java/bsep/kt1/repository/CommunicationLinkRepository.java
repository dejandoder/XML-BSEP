package bsep.kt1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bsep.kt1.model.CommunicationLink;

public interface CommunicationLinkRepository extends JpaRepository<CommunicationLink, Long> {
	
	CommunicationLink save(CommunicationLink cl);
	
	List<CommunicationLink> findAll();
	
	@Query("select link from CommunicationLink link where (link.memberOne.serialNumber = :s1 and link.memberOne.serialNumber = :s2) or (link.memberOne.serialNumber = :s2 and link.memberOne.serialNumber = :s1) ")
	List<CommunicationLink> checkCommunication(@Param("s1") long s1, @Param("s2") long s2);

}
