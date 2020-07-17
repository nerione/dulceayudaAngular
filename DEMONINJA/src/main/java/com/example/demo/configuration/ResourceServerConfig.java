package com.example.demo.configuration;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators.And;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
		.antMatchers(HttpMethod.GET, "/api/contacts", "/api/contacts/pagina/**", "/images/**", "/api/contacts/file/**", "/api/sms", "/sat/declaracion").permitAll() //permite mostrar todos los contactos y paginar y ver la foto
		.antMatchers(HttpMethod.GET, "/api/contacts/**").hasRole("ADMIN")//PARA PERMITIR LA CONSULTA DEL CONTACTO POR SU ID
		.antMatchers(HttpMethod.DELETE, "/api/contacts/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/api/contacts").hasRole("ADMIN")//permite persistir un nuevo contacto a la vez

		/*.antMatchers(HttpMethod.GET, "/api/contacts/**").hasAnyRole("USER", "ADMIN")//Recuperar contacto por ID
		.antMatchers(HttpMethod.POST, "/api/contacts/upload").hasAnyRole("USER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/contacts").hasRole("ADMIN")//permite persistir un nuevo contacto a la vez
		.antMatchers(HttpMethod.DELETE, "/api/contacts/**").hasRole("ADMIN") //permite eliminar un contacto
		.antMatchers("/api/contacts/**").hasRole("ADMIN")//Se aplica para todos los metodos (post, get, put and delete) no especificados arriba.*/
		.anyRequest().authenticated()// cualquiere otra peticion esta permitida solo con autenticacion
		//agregamos la configuracion del CORS al httSecurity
		.and()
		.cors().configurationSource(corsConfigurationSource());
	}
	
	
	//Configuracion CORS spring oauth2
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsSonfig = new CorsConfiguration();
		//Para despliegues en ambientes productivos, es necesario agregar a la linea de abajo, el dominio, ruta o ip donde se encuentre desplegado el front. 
		//El * indica que acepte peticiones de donde sea. Por ahora esta bien pero no es recomendable
		corsSonfig.setAllowedOrigins(Arrays.asList("http://localhost:4200","*"));
		corsSonfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsSonfig.setAllowCredentials(true);
		corsSonfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsSonfig);
		
		return source;
		
	}
	
	//Registro del filtro en la prioridad mas alta de spring. Se aplica al servidor de autorizacion, asi como para validar el token
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		//Entre mas bajo el orden, mas alta es la prioridad
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filter;
	}
	
	
	
	
	

}
