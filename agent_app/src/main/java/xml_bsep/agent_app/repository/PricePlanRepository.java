package xml_bsep.agent_app.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.PricePlan;

public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

	@Query("select pricePlan from PricePlan pricePlan where pricePlan.accomodationUnit.id = :id")
	public List<PricePlan> getPricePlansByAccomodationUnit(@Param("id") long id);

	@Query("select count(*) from PricePlan pricePlan where ((pricePlan.fromDate <= :toDate and pricePlan.toDate >= :toDate) or (pricePlan.fromDate <= :fromDate and pricePlan.toDate >= :fromDate) or (pricePlan.toDate >= :toDate and pricePlan.fromDate <= :fromDate)) and pricePlan.accomodationUnit.id = :accId")
	public int checkIfPlanIsValid(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate, @Param("accId") long accId);
	
}
