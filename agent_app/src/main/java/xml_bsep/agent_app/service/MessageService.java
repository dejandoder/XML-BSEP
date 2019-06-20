package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.dto.UserDTO;
import xml_bsep.agent_app.model.Message;
import xml_bsep.agent_app.model.SyncMessagesResponse;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.MessageRepository;
import xml_bsep.agent_app.soap_clients.MessagesServiceSoapClient;

@Service
public class MessageService {
	
	@Autowired
	MessageRepository repository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	MessagesServiceSoapClient messageSoap;
	
	public void syncMessages(){
		SyncMessagesResponse response= messageSoap.syncMessages();
		if (response.getMessages()==null) return;
		repository.saveAll(response.getMessages());
	}
	
	public List<UserDTO> getContacts(){
		User currentUser=userService.getCurrentUser();
		List<User> users=reservationService.findAgentsContacts(currentUser.getId());
		List<UserDTO> usersDTO= new ArrayList<UserDTO>();
		
		for(User user:users){
			
			UserDTO temp=new UserDTO(user.getId(), user.getName(), user.getSurname(), user.getUsername());
			if(!usersDTO.contains(temp)) usersDTO.add(temp);
		}
		
		return usersDTO;
	}
	public List<Message> getAllMessages(long user2){
		User currentUser=userService.getCurrentUser();
		List<Message> messages=repository.getMessagesBetweenUsers(currentUser.getId(), user2);
		
		messages.sort((o1,o2) -> o2.getDate().compareTo(o1.getDate()));

		return messages;
	}
}
