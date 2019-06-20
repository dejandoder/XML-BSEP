package xml_bsep.agent_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import xml_bsep.agent_app.model.Reservation;
import xml_bsep.agent_app.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select res.user from Reservation res where res.accUnit.agent.id=:id")
	public List<User> findAgentsContacts(@Param("id") long id);
	
	@Query("select res from Reservation res where res.accUnit.id = :accId")
	public List<Reservation> getReservationsByAccomodationUnit(@Param("accId") long accId);
	
	public Reservation findOneById(long id);

}
