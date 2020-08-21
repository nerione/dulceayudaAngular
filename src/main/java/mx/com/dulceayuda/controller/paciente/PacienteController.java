package mx.com.dulceayuda.controller.paciente;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.model.Usuario;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/paciente")
public class PacienteController {
	
	
	
	@GetMapping(path = "/citas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getGenerales(@RequestParam(name = "id") String id){
		log.info("Búsqueda de usuario {} ", id);
		return null;
	}
	
	
	@PostMapping(path = "/citas/{idUser}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> citas(@PathVariable (value = "idUser") String idUser){
		log.info("idUser : {} ", idUser);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return null;
	}
	
	/*@GetMapping(path = "/")
	
	@PostMapping(path = "/comentario")
	
	@GetMapping(path = "/expendiente")*/

}