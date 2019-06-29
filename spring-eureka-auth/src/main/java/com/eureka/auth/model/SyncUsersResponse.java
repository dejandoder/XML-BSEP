package com.eureka.auth.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
   "users"
})
@XmlRootElement(name = "sync_users_response")
public class SyncUsersResponse {

	@XmlElement(required = true)
	private ArrayList<User> users;
	
	public SyncUsersResponse() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	
	
}
