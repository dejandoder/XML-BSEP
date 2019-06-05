package xml_bsep.admin_service.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.common.model.User;
import com.eureka.common.model.UserStatus;
import com.eureka.common.security.UserRole;

import xml_bsep.admin_service.dto.UserDTO;
import xml_bsep.admin_service.service.UserService;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping(value = "/addNewAgent", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> addNewAgent(@RequestBody User user){
		//provjera jedinstvenosti piba i username
		if(!service.checkAgentsByPibAndName(user.getUsername(), user.getPib()).isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		user.setRole(UserRole.AGENT);
		user.setStatus(UserStatus.ACTIVATED);
		service.save(user);
		List<UserDTO> usersDTO = service.getAgents();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/activateUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> activateUser(@RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.NOT_ACTIVATED) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.activateUser(user.getId());
			return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/blockUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> blockUser(@RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.ACTIVATED) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.blockUser(user.getId());
			return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/getAgents")
	public ResponseEntity<List<UserDTO>> getAgents(){
		List<UserDTO> usersDTO = service.getAgents();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getRegularUsers")
	public ResponseEntity<List<UserDTO>> getRegularUsers(){
		List<UserDTO> usersDTO = service.getUsers();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
}
