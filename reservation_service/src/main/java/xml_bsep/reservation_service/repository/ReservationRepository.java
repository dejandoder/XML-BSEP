package xml_bsep.reservation_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eureka.common.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("select res from Reservation res where res.accUnit.id = :accId")
	public List<Reservation> getReservationsByAccUnit(@Param("accId") long accId);

}
