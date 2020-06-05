package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	//REGLAS POR EL LADO DE OAUTH2
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//METODO 1 PARA CONFIGURAR SEGURIDAD PARA OAUTH2. POR UNA CLASE. Y TAMBIEN SE PUEDE HACER DIRECTAMENTE EN LOS CONTROLADORES MEDIANTE ANOTACIONES @Segure 
		// y @EnabledGlobalMethod que se habilita en la configuracion de spriung security (Clase que extiende de WebSecurityConfigurerAdapter)
		
		http.authorizeRequests()
		//Cualquier petion a esta ruta no requiere autenticacion
		.antMatchers(HttpMethod.GET, "/api/contacts", "/api/contacts/pagina/**", "/api/contacts/file/**").permitAll() //permite mostrar todos los contactos y paginar y ver la foto
		.antMatchers(HttpMethod.GET, "/api/contacts/**").hasAnyRole("USER", "ADMIN")//Recuperar contacto por ID
		.antMatchers(HttpMethod.POST, "/api/contacts/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/contacts").hasRole("ADMIN")//permite persistir un nuevo contacto a la vez
		.antMatchers(HttpMethod.DELETE, "/api/contacts/**").hasRole("ADMIN") //permite eliminar un contacto
		.antMatchers("/api/contacts/**").hasRole("ADMIN")//Se aplica para todos los metodos (post, get, put and delete) no especificados arriba.*/
		.anyRequest().authenticated();// cualquiere otra peticion esta permitida solo con autenticacion
	}
	
	

}
