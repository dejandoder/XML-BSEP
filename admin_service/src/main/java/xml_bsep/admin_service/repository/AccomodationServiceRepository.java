package xml_bsep.admin_service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.AccomodationService;

public interface AccomodationServiceRepository extends JpaRepository<AccomodationService, Long> {
	
	@SuppressWarnings("unchecked")
	public AccomodationService save(AccomodationService accService);
	
	public List<AccomodationService> findAll();
	
	@Transactional
	@Modifying
	@Query("delete from AccomodationService where id = :id")
	public void delete(@Param("id") long id);
	
	public List<AccomodationService> findAccomodationServiceByName(String name);
}
