package bsep.kt1.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import bsep.kt1.model.CommunicationLink;

public interface CommunicationLinkRepository extends JpaRepository<CommunicationLink, Long> {
	
	CommunicationLink save(CommunicationLink cl);
	
	List<CommunicationLink> findAll();

}
