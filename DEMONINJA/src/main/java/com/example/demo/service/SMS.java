package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.AuthTokenResponse;

public interface SMS {
	
	public abstract ResponseEntity<AuthTokenResponse> getToken();
	
	public abstract String sendOTP(String token, String otp, String numTel);

}
