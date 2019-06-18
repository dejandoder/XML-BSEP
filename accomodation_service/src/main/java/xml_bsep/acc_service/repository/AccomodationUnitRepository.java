package xml_bsep.acc_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.AccomodationType;
import com.eureka.common.model.AccomodationUnit;

public interface AccomodationUnitRepository extends JpaRepository<AccomodationUnit, Long> {

	@Query("select accUnit from AccomodationUnit accUnit where accUnit.capacity = :persons and accUnit.accomodationType = :type and accUnit.category = :category and accUnit.cancelingPeriod >= :cancelingPeriod")
	public List<AccomodationUnit> searchAccUnits(@Param("persons") int persons, @Param("type") AccomodationType type, @Param("category") int category, @Param("cancelingPeriod") int cancelingPeriod);
}
