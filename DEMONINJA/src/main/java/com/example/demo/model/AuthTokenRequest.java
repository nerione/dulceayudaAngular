package com.example.demo.model;


public class AuthTokenRequest {
	
	public AuthTokenRequest() {	}
	
	
	public AuthTokenRequest(String apikey) {
		super();
		this.apikey = apikey;
	}

	private String apikey;
	
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}


}
