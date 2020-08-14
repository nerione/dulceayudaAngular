package mx.com.dulceayuda.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(path = "/hola")
public class HolaMundoController {
	
	@GetMapping(path = {"/mundo"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public String mundo() {
		
		log.info("");
		
		return "Hola mundo";
	}

}
