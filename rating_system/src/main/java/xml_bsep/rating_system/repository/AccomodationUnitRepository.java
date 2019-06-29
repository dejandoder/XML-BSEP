package xml_bsep.rating_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import  xml_bsep.rating_system.model.AccomodationUnit;


public interface AccomodationUnitRepository extends JpaRepository<AccomodationUnit, Long> {

	public AccomodationUnit findOneById(long id);
	
}

