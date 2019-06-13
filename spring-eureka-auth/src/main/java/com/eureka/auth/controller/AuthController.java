package com.eureka.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.auth.repository.UserRepository;


@CrossOrigin
@RestController
@RequestMapping("/")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;

	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
}