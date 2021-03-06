package com.eureka.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.auth.dto.UserDTO;
import com.eureka.auth.model.User;
import com.eureka.auth.model.UserRole;
import com.eureka.auth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	/*public String getCurrentUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.toString();
	}*/
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public List<UserDTO> getAll(){
		List<User> users = repository.findAll();
		List<UserDTO> usersDTO= new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
 	}
	
	
	public List<UserDTO> activateUser(long id){
		repository.activateUser(id);
		return getUsers();
	}
	
	public List<UserDTO> blockUser(long id){
		repository.blockUser(id);
		return getAll();
	}
	
	public User findUserById(long id) {
		return repository.findUserById(id);
	}
	
	public List<UserDTO> getAgents(){
		List<User> users = repository.findUserByRole(UserRole.AGENT);
		List<UserDTO> usersDTO= new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
	}
	
	public List<UserDTO> getUsers(){
		List<User> users = repository.findUserByRole(UserRole.USER);
		List<UserDTO> usersDTO= new ArrayList<>();
		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
	}
	
	public List<User> checkAgentsByPibAndName(String username, String pib){
		return repository.checkAgentsByPibAndName(username, pib);
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	public User getUserByUsername(String username) {
		return repository.findUserByUsername(username);
	}
}