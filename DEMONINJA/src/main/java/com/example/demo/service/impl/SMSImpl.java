package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.AuthTokenResponse;
import com.example.demo.model.SMSRequest;
import com.example.demo.service.SMS;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SMSImpl implements SMS{
	
	private static final Logger log = LoggerFactory.getLogger(SMSImpl.class);
	
	//URI para obtener un token con vigencia de 1 hr 
	@Value("${sms.uri.token}")
	private String urlToken;
	//URI para enviar sms con otp
	@Value("${sms.uri.send}")
	private String urlSms;
	//código de autenticacion para consumir api smsmasivos.com
	@Value("${sms.uri.token.apikey}")
	private String apikey;
	//52 para México
	@Value("${sms.codigo.ciudad}")
	private int codigoPais;
	
	private RestTemplate restTemplate;

	@Override
	public ResponseEntity<AuthTokenResponse> getToken() {
		
		ResponseEntity<AuthTokenResponse> response = null;
		
		try {
			
		log.info("Solicitando token de autenticacion para");
		
		restTemplate = new RestTemplate();			
		HttpHeaders headers = new HttpHeaders();
		Map<String, String> params = new HashMap();
		params.put("apikey", apikey);
		headers.add("Content-Type", "application/json");
		headers.add("User-Agent", "");
		HttpEntity<Map> entity = new HttpEntity(params, headers);
		
		log.info("Inicia peticion para solicitar token .........................");
		
		response = restTemplate.postForEntity("https://api.smsmasivos.com.mx/auth", entity, AuthTokenResponse.class);
		
		}catch(Exception e) {
			log.error("Problema al intentar obtener un token : " + e.getMessage());
		}
	
		return response;
	}

	@Override
	public String sendOTP(String token, String otp, String smsNumber) {
		
		ResponseEntity<String> response = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("token", token);
			headers.add("User-Agent", "");
			
			SMSRequest sMSRequest = new SMSRequest();
			sMSRequest.setCountry_code(codigoPais);
			sMSRequest.setMessage("Dulce Ayuda. Este es tu codigo verificador : " + otp + " Ingresalo para continuar");
			sMSRequest.setNumbers(smsNumber);
			//1 para modo de pruebas con hasta 1 mil pruebas por dia. 0 para modo produccion.
			sMSRequest.setSandbox(0);
			//mapper.writeValueAsString(sMSRequest);
			HttpEntity<String> request = new HttpEntity<String>(mapper.writeValueAsString(sMSRequest), headers);
			
			response = restTemplate.postForEntity(urlSms, request, String.class);
			
		}catch (Exception e) {
			log.error("Erro al intentar enviar sms " + e.getMessage());
		}
		
		return response.getBody();
		
	}

}
