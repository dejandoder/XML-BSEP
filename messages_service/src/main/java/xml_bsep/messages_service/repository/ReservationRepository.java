package xml_bsep.messages_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.eureka.common.model.Reservation;
import com.eureka.common.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select distinct res.accUnit.agent from Reservation res where res.user.username = :username")
	public List<User> getContacts(@Param("username") String username);
	
}
