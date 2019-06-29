package com.eureka.auth.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.dto.UserDTO;
import com.eureka.auth.repository.UserRepository;
import com.eureka.auth.service.UserService;
import com.eureka.common.model.User;
import com.eureka.common.model.UserRole;
import com.eureka.common.model.UserStatus;


@RestController
@RequestMapping("")
public class UserController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService service;
	
	@Autowired 
	UserRepository repository;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@PreAuthorize("hasAuthority('ADD_AGENT')")
	@PostMapping(value = "/admin/addNewAgent", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> addNewAgent(@Valid @RequestBody User user){
		//provjera jedinstvenosti piba i username
		if(!service.checkAgentsByPibAndName(user.getUsername(), user.getPib()).isEmpty()) {
			//ocdjee
			//logger.info("NP_EVENT DA {} {}", service.getCurrentUserName(), user.getId());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		user.setRole(UserRole.AGENT);
		user.setStatus(UserStatus.ACTIVATED);
		user.setPassword(encoder.encode(user.getPassword()));
		service.save(user);
		List<UserDTO> usersDTO = service.getAgents();
		//logger.info("NP_EVENT DA {} {}", service.getCurrentUserName(), user.getId());
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ACTIVATE_USER')")
	@PostMapping(value = "/admin/activateUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> activateUser( @Valid @RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		System.out.println(user.toString());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.NOT_ACTIVATED) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.activateUser(user.getId());
			//logger.info("NP_EVENT DOK {} {}", service.getCurrentUserName(), user.getId());
			return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
		}
	}
	
	@PreAuthorize("hasAuthority('BLOCK_USER')")
	@PostMapping(value = "/admin/blockUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> blockUser(@Valid @RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.ACTIVATED) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.blockUser(user.getId());
			//logger.info("NP_EVENT BOK {} {}", service.getCurrentUserName(), user.getId());
			return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/admin/getAgents")
	public ResponseEntity<List<UserDTO>> getAgents(){
		List<UserDTO> usersDTO = service.getAgents();
		//logger.info("NP_EVENT PSA {}", service.getCurrentUserName());
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/getRegularUsers")
	public ResponseEntity<List<UserDTO>> getRegularUsers(){
		List<UserDTO> usersDTO = service.getUsers();
		//logger.info("NP_EVENT PSK {}", service.getCurrentUserName());
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/user/getUser")
	public ResponseEntity<User> getUser(@RequestBody @Pattern(regexp = "/[a-zA-Z0-9.,?]*/") String username){
		User user = service.getUserByUsername(username);
		//logger.info("NP_EVENT PSK {}", service.getCurrentUserName());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/registration")
	public ResponseEntity registration(@RequestBody UserDTO userDTO){
		User user = new User();
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		user.setUsername(userDTO.getUsername());
		user.setPassword(encoder.encode(userDTO.getPassword()));
		user.setRole(UserRole.USER);
		user.setStatus(UserStatus.NOT_ACTIVATED);
		
		if(repository.findUserByUsername(userDTO.getUsername()) != null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}else {
			service.save(user);
			return new ResponseEntity(HttpStatus.OK);
		}
	}
	
}
