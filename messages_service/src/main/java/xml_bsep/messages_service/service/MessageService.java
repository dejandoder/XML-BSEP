package xml_bsep.messages_service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eureka.common.model.Message;
import com.eureka.common.model.User;

import xml_bsep.messages_service.dto.MessageDTO;
import xml_bsep.messages_service.dto.UserDTO;
import xml_bsep.messages_service.repository.MessageRepository;
import xml_bsep.messages_service.repository.ReservationRepository;

@Service
public class MessageService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	MessageRepository repository;
	
	@Autowired
	ReservationRepository resRepository;
	
	@Autowired
	UserService userService;

	public List<Message> findAll(){
		return repository.findAll();
	}
	
	public List<UserDTO> getContacts(){
		List<User> users = resRepository.getContacts(userService.getCurrentUsername());
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
	}
	
	public List<MessageDTO> getMessages(String username1){
		List<Message> messages = repository.getMessages(username1, userService.getCurrentUsername());
		
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
	
	public void sendMessage(MessageDTO messDTO) {
		Message message = new Message();
		
		HttpEntity<String> fromUserRequest = new HttpEntity<>(userService.getCurrentUsername());
		ResponseEntity<User> fromUserResponse = restTemplate.exchange("http://auth-service/user/getUser", HttpMethod.POST, fromUserRequest, User.class);
	
		HttpEntity<String> toUserRequest = new HttpEntity<>(messDTO.getUsername2());
		ResponseEntity<User> toUserResponse = restTemplate.exchange("http://auth-service/user/getUser", HttpMethod.POST, toUserRequest, User.class);
		
		message.setContent(messDTO.getContent());
		message.setFromUser(fromUserResponse.getBody());
		message.setToUser(toUserResponse.getBody());
		message.setDate(new Date());
	
		repository.save(message);
	}
	
	public Message save(Message message) {
		return repository.save(message);
	}
}
