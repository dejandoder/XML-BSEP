package xml_bsep.messages_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xml_bsep.messages_service.dto.MessageDTO;
import xml_bsep.messages_service.dto.UserDTO;
import xml_bsep.messages_service.service.MessageService;
import xml_bsep.messages_service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("")
public class MessagesController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageService messService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/getContacts")
	public ResponseEntity<List<UserDTO>> getContacts(){
		logger.info("NP_EVENT PKK {}", userService.getCurrentUsername());
		return new ResponseEntity<List<UserDTO>>(messService.getContacts(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/getMessages")
	public ResponseEntity<List<MessageDTO>> getMessages(@RequestBody String username){
		logger.info("NP_EVENT PP {} {}", userService.getCurrentUsername(), username);   ////////////
		return new ResponseEntity<>(messService.getMessages(username), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/sendMessage")
	public ResponseEntity sendMessage(@RequestBody MessageDTO messageDTO) {
		messService.sendMessage(messageDTO);
		logger.info("NP_EVENT SP {} {}", messageDTO.getUserId1(), messageDTO.getUserId2());
		return new ResponseEntity(HttpStatus.OK);
	}
}
