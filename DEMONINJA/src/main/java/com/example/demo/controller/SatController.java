package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.SatDeclaraResponse;
import com.example.demo.service.SatService;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping(path = "/sat")
public class SatController {

	private static final Logger logger = LoggerFactory.getLogger(SatController.class);


	@Autowired
	private SatService satService;

	@GetMapping(path = "/declaracion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SatDeclaraResponse> declara() {

		
		logger.info("Inicia procesamiento de archivos XML para obtener monto de declaracion RIF");

		logger.info(System.getenv("classpath").toString());
		
		
		SatDeclaraResponse response = satService.getResumeFacturas();
		
		return new ResponseEntity<SatDeclaraResponse>(response, HttpStatus.OK); 
			
	}

}
