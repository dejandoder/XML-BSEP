package xml_bsep.rating_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import xml_bsep.rating_system.model.User;
import xml_bsep.rating_system.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public String getCurrentUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return username;
	}

	public String getJwtToken() {
		String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
		return token;
	}
	
	public User getCurrentUser() {
		return repository.findOneByUsername(getCurrentUsername());
	}
}
