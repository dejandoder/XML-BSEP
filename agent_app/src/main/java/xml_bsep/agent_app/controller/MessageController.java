package xml_bsep.agent_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.agent_app.model.SendMessageRequest;
import xml_bsep.agent_app.model.SendMessageResponse;
import xml_bsep.agent_app.model.SyncMessagesRequest;
import xml_bsep.agent_app.model.SyncMessagesResponse;
import xml_bsep.agent_app.soap_clients.MessagesServiceSoapClient;

@RestController
@RequestMapping("/")
public class MessageController {
	
	@Autowired
	MessagesServiceSoapClient client;

	@PostMapping(value = "/syncMessages")
	public ResponseEntity<SyncMessagesResponse> syncMessages(){
		return new ResponseEntity<>(client.syncMessages(new SyncMessagesRequest()), HttpStatus.OK);
	}
	
	@PostMapping(value = "/sendMessage")
	public ResponseEntity<SendMessageResponse> sendMessage(){
		return new ResponseEntity<>(client.sendMessage(new SendMessageRequest()), HttpStatus.OK);
	}
}
