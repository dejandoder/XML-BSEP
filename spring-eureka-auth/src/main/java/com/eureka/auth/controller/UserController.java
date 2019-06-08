package com.eureka.auth.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.dto.UserDTO;
import com.eureka.auth.service.UserService;
import com.eureka.common.model.User;
import com.eureka.common.model.UserRole;
import com.eureka.common.model.UserStatus;


@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	UserService service;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@PostMapping(value = "/admin/addNewAgent", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> addNewAgent(@RequestBody User user){
		//provjera jedinstvenosti piba i username
		if(!service.checkAgentsByPibAndName(user.getUsername(), user.getPib()).isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		user.setRole(UserRole.AGENT);
		user.setStatus(UserStatus.ACTIVATED);
		user.setPassword(encoder.encode(user.getPassword()));
		service.save(user);
		List<UserDTO> usersDTO = service.getAgents();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/admin/activateUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> activateUser(@RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		System.out.println(user.toString());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.NOT_ACTIVATED) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.activateUser(user.getId());
			return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/admin/blockUser", consumes = "application/json")
	public ResponseEntity<List<UserDTO>> blockUser(@RequestBody UserDTO userDTO){
		User user = service.findUserById(userDTO.getId());
		if(user.getRole() != UserRole.USER || user.getStatus() != UserStatus.ACTIVATED) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			service.blockUser(user.getId());
			return new ResponseEntity<>(service.getUsers(), HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/admin/getAgents")
	public ResponseEntity<List<UserDTO>> getAgents(){
		List<UserDTO> usersDTO = service.getAgents();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "/admin/getRegularUsers")
	public ResponseEntity<List<UserDTO>> getRegularUsers(){
		List<UserDTO> usersDTO = service.getUsers();
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
}
