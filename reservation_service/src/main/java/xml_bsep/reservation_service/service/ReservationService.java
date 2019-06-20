package xml_bsep.reservation_service.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.common.model.Reservation;

import xml_bsep.reservation_service.dto.CheckReaservationDTO;
import xml_bsep.reservation_service.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository repository;
	
	public boolean checkIfAccUnitIsAvalible(CheckReaservationDTO checkReservation) {
		List<Reservation> reservations = repository.getReservationsByAccUnit(checkReservation.getAccID());
		
		if(reservations == null) return true;
		
		for(Reservation res : reservations) {
			
			Date from1 = res.getFromDate();
			Date to1 = res.getToDate();
			Date from2 = checkReservation.getDates().get(0);
			Date to2 = checkReservation.getDates().get(1);
			
			if(from2.after(from1) && to1.after(to2)) return false;
			if(from2.after(from1) && from2.before(to1)) return false;
			if(to2.after(from1) && to2.before(to1)) return false;
		}
		
		return true;
	}
	
	public List<Reservation> findAll(){
		return repository.findAll();
	}
	
}
