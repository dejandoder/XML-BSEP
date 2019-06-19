package xml_bsep.agent_app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import xml_bsep.agent_app.model.SyncUsersResponse;
import xml_bsep.agent_app.model.User;
import xml_bsep.agent_app.repository.UserRepository;
import xml_bsep.agent_app.soap_clients.AuthServiceSoapClient;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	AuthServiceSoapClient soapClient;
	
	
	public User getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return repository.findByUsername(principal.toString());
	}

	public String getJwtToken() {
		return SecurityContextHolder.getContext().getAuthentication().getDetails().toString();
	}

	public void syncUsers(){
		SyncUsersResponse response = soapClient.syncUsersRequest();
		if (response.getUsers()==null) return;
		repository.saveAll(response.getUsers());
	}
}


