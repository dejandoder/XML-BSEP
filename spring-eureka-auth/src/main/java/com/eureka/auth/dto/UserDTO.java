package com.eureka.auth.dto;

import com.eureka.common.model.User;
import com.eureka.common.model.UserRole;
import com.eureka.common.model.UserStatus;

public class UserDTO {

    protected String name;
    protected String surname;
    protected long id;
    protected String email;
    protected String username;
    protected String pib;
    protected UserRole role;
    protected UserStatus status;
  
    public UserDTO(User user) {
    	this.name = user.getName();
    	this.surname = user.getSurname();
    	this.id = user.getId();
    	this.email = user.getEmail();
    	this.username = user.getUsername();
    	this.pib = user.getPib();
    	this.role = user.getRole();
    	this.status = user.getStatus();
    	
    }
	    
    public UserDTO() {
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

}
