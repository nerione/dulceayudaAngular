package com.example.demo.utils;

import java.security.SecureRandom;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*Clase para obtener el token*/
@Component
public class Utilities {
	
	private static final Logger log = LoggerFactory.getLogger(Utilities.class);
	
	public static final String STRINGNUMEROS="1234567890";
	
	public static final int TAMANIO_TOKEN = 4;
	
	
	//GENERACION OTP PARA VALIDACION NUM TEL	
	public static String generateOTP()
	{
		
		StringBuilder builder = new StringBuilder();
		SecureRandom ranGen = new SecureRandom();
		
		for (int i = 0; i < TAMANIO_TOKEN ; i++) {
			int character = ranGen.nextInt(STRINGNUMEROS.length());
			builder.append(character);
		}
		System.out.println(builder);
		return builder.toString();
	}
	
	//Utileria para obtener nombres de archivos en un pathFolder
	public static List<String> listaDeArchivos(){
		
		
		return null;
	}


}
