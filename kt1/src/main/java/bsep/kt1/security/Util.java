package bsep.kt1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import bsep.kt1.repository.UserRepository;
import bsep.kt1.model.User;

@Component
public class Util {
	
	@Autowired
	private UserRepository repo;
	
	public User getCurrentUser() {
		String userEmail;
		Authentication currentUserAuth;
		currentUserAuth = SecurityContextHolder.getContext().getAuthentication();
		if(currentUserAuth != null) {
			userEmail = currentUserAuth.getName();
			if(userEmail != null) {
				if(repo != null) {
					return repo.getUserByEmail(userEmail);
				}
				System.out.println("USER SERVICE = NULL");
			}
			return null;
		}
		return null;
	}
}
