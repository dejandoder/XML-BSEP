package com.eureka.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.model.User;
import com.eureka.auth.repository.UserRepository;
import com.eureka.common.security.UserRole;

@RestController
@RequestMapping("/")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;

	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@PostMapping(value = "addUsers")
	public void addUsers() {
		
		User u1 = new User();
		u1.setEmail("admin@gmail.com");
		u1.setName("Admir");
		u1.setSurname("Admir");
		u1.setRole(UserRole.ADMIN);
		u1.setUsername("admin");
		u1.setPassword(encoder.encode("admin123"));
		userRepository.save(u1);
		
		User u2 = new User();
		u2.setEmail("user@gmail.com");
		u2.setName("User");
		u2.setSurname("User");
		u2.setRole(UserRole.ADMIN);
		u2.setUsername("user");
		u2.setPassword(encoder.encode("user123"));
		userRepository.save(u2);
	}
	
}
