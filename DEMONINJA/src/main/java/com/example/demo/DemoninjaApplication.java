package com.example.demo;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoninjaApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder encripta;

	public static void main(String[] args) {
		SpringApplication.run(DemoninjaApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		//System.out.println("CONTRASEÑA : " + encripta.encode("nerione"));
		
		
	}

}
