package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${sat.facturas.emitidas.path}")
	private String pathEmitidas;

	@Value("${sat.facturas.recibidas.path}")
	private String pathRecibidas;

	@Autowired
	private SatService satService;

	@GetMapping(path = "/declaracion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SatDeclaraResponse> declara() {

		logger.info("Inicia procesamiento de archivos XML para obtener monto de declaracion RIF");
		logger.info("Emitidas path {} ", pathEmitidas);
		logger.info("Recibidas path {} ", pathRecibidas);
		logger.info(System.getenv("classpath").toString());
		
		File filesEmitidas = new File(pathEmitidas);
		File filesRecibidas = new File(pathRecibidas);
		
		ArrayList<File> fileListEmitidas = new ArrayList<File>(Arrays.asList(filesEmitidas.listFiles()));
		ArrayList<File> fileListRecibidas = new ArrayList<File>(Arrays.asList(filesRecibidas.listFiles()));

		SatDeclaraResponse response = satService.getResumeFacturas(fileListEmitidas, fileListRecibidas);
		
		return new ResponseEntity<SatDeclaraResponse>(response, HttpStatus.OK); 
			
	}

}
