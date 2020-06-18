package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.AuthTokenRequest;
import com.example.demo.model.AuthTokenResponse;

/*Clase para obtener el token*/
@Component
public class Utilities {
	
	private static final Logger log = LoggerFactory.getLogger(Utilities.class);
	
	@Value("${sms.uri.token}")
	private String tokenURI;
	
	@Value("${sms.uri.token.apikey}")
	private String apikey;
	
	private RestTemplate restTemplate;
	
	//@Value("${}")
	//private String
	
	public ResponseEntity<AuthTokenResponse> getToken() {
		

		restTemplate = new RestTemplate();
		
		//AuthTokenRequest request = new AuthTokenRequest();
		//request.setApikey("cd3592eee426ec9b97e58c24cba6a874878f1514");
		ResponseEntity<AuthTokenResponse> response = null;

		try {
			
			log.info("Solicitando token de autenticacion para");
			
			//log.info(request.toString());
			
			HttpHeaders headers = new HttpHeaders();
			String requestJson = "{\"apikey\":\"cd3592eee426ec9b97e58c24cba6a874878f1514.\"}";
			
			Map<String, String> params = new HashMap();
			
			params.put("apikey", "cd3592eee426ec9b97e58c24cba6a874878f1514");
			headers.add("Content-Type", "application/json");
			//headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			//HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
			HttpEntity<Map> entity = new HttpEntity(params, headers);
			
			log.info("Request headers {} ",entity);
			log.info("Inicia peticion para solicitar token. .... ....................");
			//ResponseEntity<String> s = restTemplate.getForEntity("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=439d4b804bc8187953eb36d2a8c26a02", String.class);
			ResponseEntity<String> respuesta = restTemplate.postForEntity("https://api.smsmasivos.com.mx/auth", entity, String.class);
			//ResponseEntity<AuthTokenResponse>  responseS = 
			///log.info("Respuesta servicio token {}", responseS.toString());
			
			
		}catch (Exception e) {
			log.error("ERROR al intentar obtener el token  {}", e);
		}
		
		return null;
			
		
	}

}
