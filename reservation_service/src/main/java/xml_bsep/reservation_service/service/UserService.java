package xml_bsep.reservation_service.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String getCurrentUsername() {
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return username;
	}

	public String getJwtToken() {
		String token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
		return token;
	}
}
