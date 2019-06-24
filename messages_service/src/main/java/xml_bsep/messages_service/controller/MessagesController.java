package xml_bsep.messages_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xml_bsep.messages_service.dto.MessageDTO;
import xml_bsep.messages_service.dto.UserDTO;
import xml_bsep.messages_service.service.MessageService;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("")
@Validated
public class MessagesController {

	@Autowired
	MessageService messService;
	
	@GetMapping(value = "/getContacts")
	public ResponseEntity<List<UserDTO>> getContacts(){
		return new ResponseEntity<List<UserDTO>>(messService.getContacts(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/getMessages")
	public ResponseEntity<List<MessageDTO>> getMessages(@RequestBody @Size(min=1,max=40) String username){
		return new ResponseEntity<>(messService.getMessages(username), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/sendMessage")
	public ResponseEntity sendMessage(@RequestBody @Valid MessageDTO messageDTO) {
		messService.sendMessage(messageDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
