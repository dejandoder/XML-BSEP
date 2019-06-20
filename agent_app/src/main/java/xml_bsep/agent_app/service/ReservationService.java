package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.dto.ReservationDTO;
import xml_bsep.agent_app.model.AgentReservationRequest;
import xml_bsep.agent_app.model.AgentReservationResponse;
import xml_bsep.agent_app.model.ApproveReservationRequest;
import xml_bsep.agent_app.model.ApproveReservationResponse;
import xml_bsep.agent_app.model.ConfirmReservationRequest;
import xml_bsep.agent_app.model.ConfirmReservationResponse;
import xml_bsep.agent_app.model.DeclineReservationRequest;
import xml_bsep.agent_app.model.Reservation;
import xml_bsep.agent_app.model.ReservationStatus;
import xml_bsep.agent_app.model.SOAPResponseStatus;
import xml_bsep.agent_app.model.SyncReservationsResponse;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.AccomodationUnitRepository;
import xml_bsep.agent_app.repository.ReservationRepository;
import xml_bsep.agent_app.soap_clients.ReservationServiceSoapClient;

@Service
public class ReservationService {
	
	@Autowired
	ReservationServiceSoapClient reservationSoap;
	
	@Autowired
	ReservationRepository repository;
	
	@Autowired
	AccomodationUnitRepository accRepository;
	
	@Autowired
	UserService userService;
	
	public void syncReservations(){
		SyncReservationsResponse response= reservationSoap.syncReservation();
		if(response.getReservations()==null) return;
		repository.saveAll(response.getReservations());
	}
	public List<User> findAgentsContacts(long id){
		return repository.findAgentsContacts(id);
	}
	
	public List<ReservationDTO> getReservationsByAccUnit(long accId){
		List<Reservation> reservations =  repository.getReservationsByAccomodationUnit(accId);
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationsDTO.add(new ReservationDTO(reservation));
		}
		
		return reservationsDTO;
	}
	
	public void doAgentReservation(ReservationDTO resDTO) {
		Reservation res = new Reservation();
		res.setAccUnit(accRepository.findOneById(resDTO.getAccId()));
		res.setAgentReserved(true);
		res.setFromDate(resDTO.getFromDate());
		res.setToDate(resDTO.getToDate());
		res.setUser(userService.getCurrentUser());
		
		AgentReservationRequest request = new AgentReservationRequest();
		request.setReservation(res);
		
		AgentReservationResponse response = reservationSoap.agentReservation(request);
		
		res.setId(response.getReservationId());
		
		repository.save(res);
	}
	
	public boolean approveReservation(long resId) {
		ApproveReservationRequest request = new ApproveReservationRequest();
		request.setReservationId(resId);
		
		ApproveReservationResponse response = reservationSoap.approveReservation(request);
		
		if(response.getStatus() == SOAPResponseStatus.SUCCESS) {
			Reservation res = repository.findOneById(resId);
			res.setStatus(ReservationStatus.APPROVED);
			repository.save(res);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean confirmReservation(long resId) {
		ConfirmReservationRequest request = new ConfirmReservationRequest();
		request.setReservationId(resId);
		
		ConfirmReservationResponse response = reservationSoap.confirmReservation(request);
		
		if(response.getStatus() == SOAPResponseStatus.SUCCESS) {
			Reservation res = repository.findOneById(resId);
			res.setStatus(ReservationStatus.CONFIRMED);
			repository.save(res);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean declineReservation(long resId) {
		DeclineReservationRequest request = new DeclineReservationRequest();
		request.setResId(resId);
		reservationSoap.declineReservation(request);
		repository.deleteById(resId);
		return true;
	}
}
