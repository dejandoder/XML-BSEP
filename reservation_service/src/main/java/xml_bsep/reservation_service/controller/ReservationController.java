package xml_bsep.reservation_service.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.model.AccomodationUnit;
import com.eureka.common.model.Reservation;
import com.eureka.common.model.ReservationStatus;
import com.eureka.common.model.User;

import xml_bsep.reservation_service.dto.CheckReaservationDTO;
import xml_bsep.reservation_service.dto.ReservationDTO;
import xml_bsep.reservation_service.service.ReservationService;
import xml_bsep.reservation_service.service.UserService;

@RestController
@RequestMapping("/")
public class ReservationController {

	@Autowired
	ReservationService resService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/checkIfAccUnitIsAvalible")
	public ResponseEntity<Boolean> checkIfAccUnitIsAvalible(@RequestBody CheckReaservationDTO checkReservation){
		boolean availabile = resService.checkIfAccUnitIsAvalible(checkReservation);
		return new ResponseEntity<>(availabile, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/newReservation")
	public ResponseEntity newReservation(@RequestBody ReservationDTO reservationDTO){
		
		Reservation reservation = new Reservation();
		
		HttpEntity<Long> request = new HttpEntity<>(reservationDTO.getAccId());
		ResponseEntity<AccomodationUnit> response = restTemplate.exchange("http://acc-service/getAccUnit", HttpMethod.POST, request, AccomodationUnit.class);
	
		AccomodationUnit accUnit = response.getBody();
		
		HttpEntity<String> requestUser = new HttpEntity<>(userService.getCurrentUsername());
		ResponseEntity<User> responseUser = restTemplate.exchange("http://auth-service/getUser", HttpMethod.POST, requestUser, User.class);
	
		User user = responseUser.getBody();
		
		CheckReaservationDTO crDTO = new CheckReaservationDTO();
		crDTO.setAccID(reservationDTO.getAccId());
		crDTO.setDates(new ArrayList<>());
		crDTO.getDates().add(reservationDTO.getFromDate());
		crDTO.getDates().add(reservationDTO.getToDate());
		
		boolean avalaible = resService.checkIfAccUnitIsAvalible(crDTO);
		
		if(!avalaible) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		reservation.setAccUnit(accUnit);
		reservation.setUser(user);
		reservation.setFromDate(reservationDTO.getFromDate());
		reservation.setToDate(reservationDTO.getToDate());
		reservation.setAgentReserved(false);
		reservation.setStatus(ReservationStatus.PENDING);
		
		resService.saveReservation(reservation);
		
		return new ResponseEntity<>(HttpStatus.OK);		
	}
	
}
