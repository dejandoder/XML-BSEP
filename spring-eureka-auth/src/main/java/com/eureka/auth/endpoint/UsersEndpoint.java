package com.eureka.auth.endpoint;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.eureka.auth.service.UserService;
import com.eureka.common.model.SyncUsersRequest;
import com.eureka.common.model.SyncUsersResponse;
import com.eureka.common.model.User;

@Endpoint
public class UsersEndpoint {
	
	@Autowired
	UserService userService;
	
	@ResponsePayload
	@PayloadRoot(namespace = "http://www.ftn.uns.ac.rs/hotel-team1", localPart = "sync_users_request")
	public SyncUsersResponse syncUsers(@RequestPayload SyncUsersRequest request) {
		
		ArrayList<User> users = (ArrayList<User>) userService.getAllUsers();
		SyncUsersResponse response = new SyncUsersResponse();
		response.setUsers(users);
		
		return response;
	}

}
