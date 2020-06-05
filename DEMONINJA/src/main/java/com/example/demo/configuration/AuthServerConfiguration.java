package com.example.demo.configuration;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.example.demo.component.InfoAdicionalToken;
import com.example.demo.constants.ViewConstant;
import com.example.demo.rsa.RSAKey;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	
	//En esta clase se configura los permisos de nuestros endpoints de spring security OAuth2. Este es para la generacion del token y debe ser accesibe para todos
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//permiso permitAll() a todos. Anónimo o no
		security.tokenKeyAccess("permitAll()")
		//permiso al endpoint para validar el token y su firma
		.checkTokenAccess("isAuthenticated()")
		;
	}

	//Este metodo se utiliza para registrar a cada uno de las aplicaciones cliente que tendra acceso a nuestra api Rest.(Front angular). 
	//Por cada cliente nuevo que quiera acceso, se debe registrar aqui (autenticacion de la applicacion).
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//identifiador del cliente angularApp
		clients.inMemory().withClient("angularApp")
		//password encoded de la applicacion angular
		.secret(bCryptPasswordEncoder.encode("123456789"))
		//operaciones permitidas a ese cliente
		.scopes("read", "write")
		//tipo de autenticacion tanto para el Usuario como para la Aplicacion cliente Angular. Se mandan credenciales de la app y del usuario que intenta hacer login
		.authorizedGrantTypes("password", "refresh_token")
		//tiempo de vida del token y 
		.accessTokenValiditySeconds(3600)
		//refresco del token
		.refreshTokenValiditySeconds(3600);
	}

	//Este metodo se encarga del proceso de autenticacion y validacion del token.
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		//Aqui se agrega esa informacion adicional del component InfoAdicionalToken
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		
		endpoints.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter())
		//Esto tambien es para agregar esa informacion adicional
		.tokenEnhancer(tokenEnhancerChain);
	}
	
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		//Asignamos el Codigo Secreto MAc en clave HASH
		jwtAccessTokenConverter.setSigningKey(RSAKey.RSA_PRIVATE_KEY);
		jwtAccessTokenConverter.setVerifierKey(RSAKey.RSA_PUBLIC_KEY);
		return jwtAccessTokenConverter;
		
	}
	

}
