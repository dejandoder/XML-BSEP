package xml_bsep.agent_app.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xml_bsep.agent_app.dto.MessageDTO;
import xml_bsep.agent_app.dto.UserDTO;
import xml_bsep.agent_app.model.Message;
import xml_bsep.agent_app.model.SendMessageRequest;
import xml_bsep.agent_app.model.SendMessageResponse;
import xml_bsep.agent_app.model.SyncMessagesResponse;
import xml_bsep.agent_app.service.MessageService;
import xml_bsep.agent_app.service.UserService;
import xml_bsep.agent_app.soap_clients.MessagesServiceSoapClient;

@RestController
@RequestMapping("/")
@Validated
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessagesServiceSoapClient client;
	
	@Autowired
	MessageService messageService;

	@Autowired
	UserService userService;
	
	@PostMapping(value = "/syncMessages")
	public ResponseEntity<SyncMessagesResponse> syncMessages(){
		logger.info("NP_EVENT SHP");
		return new ResponseEntity<>(client.syncMessages(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/sendMessage")
	public ResponseEntity sendMessage(@RequestBody @Valid MessageDTO messageDTO){
		messageService.sendMessage(messageDTO);
		logger.info("NP_EVENT SP {} {}", messageDTO.getUserId1(), messageDTO.getUserId2());
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getContacts")
	public ResponseEntity<List<UserDTO>> getContacts(){
		logger.info("NP_EVENT PKK {}", userService.getCurrentUserName());
		return new ResponseEntity<>(messageService.getContacts(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAllMessages")
	public ResponseEntity<List<MessageDTO>> getAllMessages(@RequestBody @Min(1) long id){
		logger.info("NP_EVENT PP {} {}", userService.getCurrentUserName(), id);
		return new ResponseEntity<>(messageService.getAllMessages(id), HttpStatus.OK);
	}
}
