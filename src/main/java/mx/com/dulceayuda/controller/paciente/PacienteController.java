package mx.com.dulceayuda.controller.paciente;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.model.Usuario;

@Slf4j
@RestController
@RequestMapping(path = "/paciente/v1")
public class PacienteController {
	
	
	
	@GetMapping(path = "/datos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getGenerales(@RequestParam(name = "id") String id){
		log.info("Búsqueda de usuario {} ", id);
		return null;
	}

}