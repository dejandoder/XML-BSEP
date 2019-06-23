package xml_bsep.agent_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import xml_bsep.agent_app.soap_clients.MessagesServiceSoapClient;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	MessagesServiceSoapClient client;
	
	@Autowired
	MessageService messageService;

	@PostMapping(value = "/syncMessages")
	public ResponseEntity<SyncMessagesResponse> syncMessages(){
		return new ResponseEntity<>(client.syncMessages(), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/sendMessage")
	public ResponseEntity sendMessage(@RequestBody MessageDTO messageDTO){
		messageService.sendMessage(messageDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "/getContacts")
	public ResponseEntity<List<UserDTO>> getContacts(){
		return new ResponseEntity<>(messageService.getContacts(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/getAllMessages")
	public ResponseEntity<List<MessageDTO>> getAllMessages(@RequestBody long id){
		return new ResponseEntity<>(messageService.getAllMessages(id), HttpStatus.OK);
	}
}
