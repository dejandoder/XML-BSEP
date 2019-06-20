package xml_bsep.agent_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.SyncReservationsResponse;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.ReservationRepository;
import xml_bsep.agent_app.soap_clients.ReservationServiceSoapClient;

@Service
public class ReservationService {
	
	@Autowired
	ReservationServiceSoapClient reservationSoap;
	
	@Autowired
	ReservationRepository repository;
	
	public void syncReservations(){
		SyncReservationsResponse response= reservationSoap.syncReservation();
		if(response.getReservations()==null) return;
		repository.saveAll(response.getReservations());
	}
	public List<User> findAgentsContacts(long id){
		return repository.findAgentsContacts(id);
	}
}
