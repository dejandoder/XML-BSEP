package bsep.kt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import bsep.kt1.model.User;
import bsep.kt1.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public User getCurrentUser() {
		String userEmail;
		Authentication currentUserAuth;
		currentUserAuth = SecurityContextHolder.getContext().getAuthentication();
		if(currentUserAuth != null) {
			userEmail = currentUserAuth.getName();
			if(userEmail != null) {
				if(repository != null) {
					return repository.getUserByEmail(userEmail);
				}
				System.out.println("USER SERVICE = NULL");
			}
			return null;
		}
		return null;
	}

}
