package com.example.demo.model;

public class AuthTokenResponse {
	
	
	
	private boolean success;
	private String message;
	private int status;
	private String code;
	private String token;
	
	
	public AuthTokenResponse() {
		
	}
	
	
	
	
	public AuthTokenResponse(boolean success, String message, int status, String code, String token) {
		super();
		this.success = success;
		this.message = message;
		this.status = status;
		this.code = code;
		this.token = token;
	}




	public boolean isSuccess() {
		return success;
	}




	public void setSuccess(boolean success) {
		this.success = success;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}




	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public String getToken() {
		return token;
	}




	public void setToken(String token) {
		this.token = token;
	}




	@Override
	public String toString() {
		return "AuthTokenResponse [success=" + success + ", message=" + message + ", status=" + status + ", code="
				+ code + ", token=" + token + "]";
	}
	
	

}
