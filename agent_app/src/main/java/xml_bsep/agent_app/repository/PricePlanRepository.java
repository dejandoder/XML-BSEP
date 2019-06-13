package xml_bsep.agent_app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.PricePlan;

public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

	@Query("select pricePlan from PricePlan pricePlan where pricePlan.accomodationUnit.id = :id")
	public List<PricePlan> getPricePlansByAccomodationUnit(@Param("id") long id);

}
