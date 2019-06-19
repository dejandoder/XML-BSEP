package xml_bsep.acc_service.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.PricePlan;

public interface PricePlanRepository extends JpaRepository<PricePlan, Long> {

	@Query("select pp.pricePerNight from PricePlan pp where pp.fromDate <= :day and pp.toDate >= :day and pp.accomodationUnit.id = :accId")
	public float getPricePlanForDay(@Param("day") Date day, @Param("accId") long accId);
}
