package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*Clase para obtener el token*/
@Component
public class Utilities {
	
	private static final Logger log = LoggerFactory.getLogger(Utilities.class);
	
	
	//GENERACION OTP PARA VALIDACION NUM TEL
	public int generateOTP() {
		int randomOTP = (int)(Math.random()*9000)+1000;
		log.info("OTP generado para usuario: " +  randomOTP);
		return randomOTP;
	}

}
