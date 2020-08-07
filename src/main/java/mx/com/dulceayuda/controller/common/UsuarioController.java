package mx.com.dulceayuda.controller.common;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.exceptions.AltaUsuarioException;
import mx.com.dulceayuda.model.Usuario;
import mx.com.dulceayuda.repository.UserRepository;
import mx.com.dulceayuda.response.Response;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class UsuarioController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	//Controlador para el registro de usuario nuevos a la plataforma
	@PostMapping(path = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> registro(@Valid @RequestBody Usuario usuario, BindingResult result){
		
		log.info("Iniciando registro de nuevo usuario");
		Response response = new Response();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<String> errores = result.getFieldErrors().parallelStream().map(error -> "Campo " + error.getField().toUpperCase() + error.getDefaultMessage()).collect(Collectors.toList());
		
		if(!errores.isEmpty()) {
			response.setCodigoRespuesta(HttpStatus.BAD_REQUEST.toString());
			response.setErrores(errores);
			response.setMensaje("Verifique que los campos obligatorios existan");
		}
		//Alta de un nuevo usuario
		try {
			userRepository.altaUsuairo(usuario);
		}catch(Exception e) {
			//log.error(e.getMessage());
		}
		
		return new ResponseEntity<Response>(response, headers, HttpStatus.BAD_REQUEST);
		
	}
	
	
	//Controlador para inicio de sesion en plataforma
	@GetMapping(path = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> inciarSesion(){
		
		//userRepository
		
		return null;
	}
	
	
	
}
