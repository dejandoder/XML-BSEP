package xml_bsep.agent_app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.Recension;

public interface RecensionRepository extends JpaRepository<Recension, Long> {

	@Query("select rec from Recension rec where rec.accomodationUnit.id = :id")
	List<Recension> getRecensionsByAccUnit(@Param("id") long id);
	
}
