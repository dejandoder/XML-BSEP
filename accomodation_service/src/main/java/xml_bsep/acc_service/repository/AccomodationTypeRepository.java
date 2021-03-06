package xml_bsep.acc_service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import xml_bsep.acc_service.model.AccomodationType;



public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {
	
	@SuppressWarnings("unchecked")
	public AccomodationType save(AccomodationType ac);
	
	public List<AccomodationType> findAll();
	
	@Transactional
	@Modifying
	@Query("delete from AccomodationType where id = :id")
	public void delete(@Param("id") long id);
	
	public List<AccomodationType> findAccomodationTypeByName(@Param("name") String name);
	
}
