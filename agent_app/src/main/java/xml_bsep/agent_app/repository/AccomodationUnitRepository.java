package xml_bsep.agent_app.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.AccomodationUnit;

public interface AccomodationUnitRepository extends JpaRepository<AccomodationUnit, Long> {


	@Query("select unit from AccomodationUnit unit where unit.agent.id = :agentId")
	public List<AccomodationUnit> getAccUnitsByAgent(@Param("agentId") long agentId);
	
	
}
