package xml_bsep.agent_app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.dto.MessageDTO;
import xml_bsep.agent_app.dto.UserDTO;
import xml_bsep.agent_app.model.Message;
import xml_bsep.agent_app.model.SendMessageRequest;
import xml_bsep.agent_app.model.SendMessageResponse;
import xml_bsep.agent_app.model.SyncMessagesResponse;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.MessageRepository;
import xml_bsep.agent_app.repository.UserRepository;
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
	
	@Autowired
	UserRepository userRepository;
	
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
	public List<MessageDTO> getAllMessages(long user2){
		User currentUser=userService.getCurrentUser();
		List<Message> messages=repository.getMessagesBetweenUsers(currentUser.getId(), user2);
		
		messages.sort((o1,o2) ->{
			if(o2.getDate().compareTo(o1.getDate()) < 0) return 1;
			return 0;
		});
		
		List<MessageDTO> messagesDTO = new ArrayList<>();
		
		for (Message message : messages) {
			messagesDTO.add(new MessageDTO(message));
		}
		
		return messagesDTO;
	}
	
	public void sendMessage(MessageDTO messageDTO) {
		
		Message message = new Message();
		
		message.setFromUser(userService.getCurrentUser());
		message.setToUser(userRepository.findByUsername(messageDTO.getUsername2()));
		message.setContent(messageDTO.getContent());
		message.setDate(new Date());
		
		SendMessageRequest request = new SendMessageRequest();
		request.setMessage(message);
		
		SendMessageResponse response = messageSoap.sendMessage(request);
		
		message.setId(response.getMessageId());
		
		repository.save(message);
	}
}
