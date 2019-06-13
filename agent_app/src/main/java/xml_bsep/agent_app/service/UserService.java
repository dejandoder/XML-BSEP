package xml_bsep.agent_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//String username = ((UserDetails)principal).getUsername();
		return repository.findByUsername(principal.toString());
	}
}
