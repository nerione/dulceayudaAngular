package com.example.demo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = -3886088376500217304L;
	
	@Id
	private String id;
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<>();
	
	public User() {}
	
	
	public User(String username, String password, boolean enabled, Set<UserRole> userRole) {
		super();
		
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	public Set<UserRole> getUserRole() {
		return userRole;
	}



	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", userRole=" + userRole + "]";
	}
	
	
	

}
