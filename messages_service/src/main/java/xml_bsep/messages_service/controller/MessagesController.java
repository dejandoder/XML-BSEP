package xml_bsep.messages_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import xml_bsep.messages_service.service.UserService;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("")
@Validated
public class MessagesController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MessageService messService;
	
	@Autowired
	UserService userService;

	@GetMapping(value = "/user/getContacts")
	public ResponseEntity<List<UserDTO>> getContacts(){
		logger.info("NP_EVENT PKK {}", userService.getCurrentUsername());
		return new ResponseEntity<List<UserDTO>>(messService.getContacts(), HttpStatus.OK);
	}
	@PostMapping(value = "/user/getMessages")//validira username na samo brojeve i slova
	public ResponseEntity<List<MessageDTO>> getMessages(@RequestBody @Pattern(regexp = "[a-zA-Z0-9.,?]*") @Size(min=1,max=40) String username){
		logger.info("NP_EVENT PP {} {}", userService.getCurrentUsername(), username); 
		return new ResponseEntity<>(messService.getMessages(username), HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/user/sendMessage")
	public ResponseEntity sendMessage(@RequestBody @Valid MessageDTO messageDTO) {
		messService.sendMessage(messageDTO);
		logger.info("NP_EVENT SP {} {}", messageDTO.getUserId1(), messageDTO.getUserId2());
		return new ResponseEntity(HttpStatus.OK);
	}
}
