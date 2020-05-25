package com.example.demo.entity;


public class UserRole {
	
	private String userRoleId;
	private String role;
	
	
	public UserRole() {}
	
	public UserRole(String userRoleId, String role) {
		this.userRoleId = userRoleId;
		this.role = role;
	}
	
	
	public String getUserRoleId() {
		return userRoleId;
	}



	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", role=" + role + "]";
	}
	
	

}
