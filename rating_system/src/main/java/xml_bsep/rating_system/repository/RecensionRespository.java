package xml_bsep.rating_system.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.rating_system.model.Recension;


public interface RecensionRespository extends JpaRepository<Recension,Long> {

	@Query("select rec from Recension rec where rec.status = xml_bsep.rating_system.model.RecensionStatus.PENDING")
	public List<Recension> getRecensionsForApproval();
	
	@Query("select rec from Recension rec where rec.accomodationUnit.id = :id ")
	public List<Recension> getRecensionsByAccUnit(@Param("id") long id);
	
	public Recension findOneById(long id);
	
	@Query("select rec from Recension rec where rec.user.id = :userId and rec.accomodationUnit.id = :accUnitId")
	public Recension checkIfRecensionExsists(@Param("userId") long userId, @Param("accUnitId") long accUnitId);
	
}
