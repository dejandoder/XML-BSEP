package xml_bsep.admin_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eureka.common.model.AccomodationService;

public interface AccomodationServiceRepository extends JpaRepository<AccomodationService, Long> {
	
	@SuppressWarnings("unchecked")
	public AccomodationService save(AccomodationService accService);
	
	public List<AccomodationService> findAll();
	
}
