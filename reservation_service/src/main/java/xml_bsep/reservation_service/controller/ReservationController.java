package xml_bsep.reservation_service.controller;


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.model.AccomodationUnit;
import com.eureka.common.model.Reservation;
import com.eureka.common.model.ReservationStatus;
import com.eureka.common.model.User;

import xml_bsep.reservation_service.UrlUtils;
import xml_bsep.reservation_service.dto.CheckReaservationDTO;
import xml_bsep.reservation_service.dto.CheckReviewDTO;
import xml_bsep.reservation_service.dto.ReservationDTO;
import xml_bsep.reservation_service.service.ReservationService;
import xml_bsep.reservation_service.service.UserService;

@RestController
@RequestMapping("/")
@Validated
public class ReservationController {

	@Autowired
	ReservationService resService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/all/checkIfAccUnitIsAvalible")
	public ResponseEntity<Boolean> checkIfAccUnitIsAvalible(@RequestBody @Valid CheckReaservationDTO checkReservation){
		boolean availabile = resService.checkIfAccUnitIsAvalible(checkReservation);
		return new ResponseEntity<>(availabile, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/newReservation")
	public ResponseEntity newReservation(@RequestBody @Valid ReservationDTO reservationDTO){
		
		Reservation reservation = new Reservation();
		
		HttpEntity<Long> request = new HttpEntity<>(reservationDTO.getAccId());
		ResponseEntity<AccomodationUnit> response = restTemplate.exchange("http://acc-service/user/getAccUnit", HttpMethod.POST, request, AccomodationUnit.class);
	
		AccomodationUnit accUnit = response.getBody();
		
		HttpEntity<String> requestUser = new HttpEntity<>(userService.getCurrentUsername());
		ResponseEntity<User> responseUser = restTemplate.exchange("http://auth-service/user/getUser", HttpMethod.POST, requestUser, User.class);
	
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
	
	@GetMapping(value = "/user/getReservationsByUser")
	public ResponseEntity<List<ReservationDTO>> getReservationsByUser(){
		String username = userService.getCurrentUsername();
		List<Reservation> reservations = resService.getReservationsByUser(username);
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		
		for (Reservation reservation : reservations) {
			CheckReviewDTO crDTO = new CheckReviewDTO(reservation.getUser().getId(),reservation.getAccUnit().getId());
			
			HttpEntity<CheckReviewDTO> request = new HttpEntity<CheckReviewDTO>(crDTO);
			HttpEntity<Boolean> response = restTemplate.exchange("https://rating-service/all/checkRecension", HttpMethod.POST, request, Boolean.class);
			
			ReservationDTO rDTO = new ReservationDTO(reservation);
			
			rDTO.setReview(response.getBody());
			
			reservationsDTO.add(rDTO);
		}
		return new ResponseEntity<List<ReservationDTO>>(reservationsDTO, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/user/deleteReservation")
	public ResponseEntity deleteResrvation(@RequestBody @Min(1) long id){
		resService.deleteReservation(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
