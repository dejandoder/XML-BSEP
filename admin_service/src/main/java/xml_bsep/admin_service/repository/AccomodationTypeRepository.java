package xml_bsep.admin_service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.eureka.common.model.AccomodationType;



public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {
	
	@SuppressWarnings("unchecked")
	public AccomodationType save(AccomodationType ac);
	
	public List<AccomodationType> findAll();
	
	@Transactional
	@Modifying
	@Query("delete from AccomodationType where id = :id")
	public void delete(@Param("id") long id);

}
