package com.example.demo.component;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

//Clase que agrega claims al token*************************************************************
@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	
	@Autowired
	private UserRepository userService;
	
	

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		User usuario_sesion = userService.getUserByUsername(authentication.getName());
		
		Map<String, Object> informacion = new HashMap();
		
		informacion.put("usuario", usuario_sesion.getUsername().toUpperCase());
		
		//informacion.put(" ", usuario_sesion.getId() + usuario_sesion.getUsername() + authentication.getAuthorities().toString());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(informacion);
		
		return accessToken;
	}
	
	

}
