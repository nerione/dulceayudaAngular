package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneraContrasenas {
	
	
	public static void main (String...strings) {
		
		String pass = "anaone";
		
		BCryptPasswordEncoder encripta = new BCryptPasswordEncoder();
		//System.out.println(encripta.encode(pass));
		
	}

}
