package mx.com.dulceayuda.controller;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.converter.UsuarioConverter;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.entity.UsuarioSequenceEntity;
import mx.com.dulceayuda.model.Usuario;
import mx.com.dulceayuda.repository.UserRepository;
import mx.com.dulceayuda.repository.UsuarioCounter;
import mx.com.dulceayuda.response.ResponseTO;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class UsuarioController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsuarioCounter usuarioCounter;
	
	@Autowired
	private BCryptPasswordEncoder encript;
	
	private Optional <UsuarioSequenceEntity> secuenciaUsuario;
	
	
	//REGISTRO DE USUARIOS. ACCESIBLE PARA TODOS SIN AUTENTICACION
	@PostMapping(path = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseTO> registro(@Valid @RequestBody Usuario usuario, BindingResult result){
		
		log.info("Iniciando registro de nuevo usuario");
		ResponseTO response = new ResponseTO();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<String> errores = result.getFieldErrors().parallelStream().map(error -> "Campo " + error.getField().toUpperCase() + error.getDefaultMessage()).collect(Collectors.toList());
		
		if(!errores.isEmpty()) {
			response.setCodigoRespuesta(HttpStatus.BAD_REQUEST.toString());
			response.setErrores(errores);
			response.setMensaje("Verifique que los campos obligatorios existan");
			return new ResponseEntity<ResponseTO>(response, headers, HttpStatus.BAD_REQUEST);
		}
		
		//Alta de un nuevo usuario
		try {
			
			log.info("IP cliente:{} ", InetAddress.getLocalHost().getHostAddress());
			
			UsuarioEntity nuevoUsuario = UsuarioConverter.userModelToEntity(usuario);
			nuevoUsuario.setUserPass(encript.encode(usuario.getUserPass()));
			secuenciaUsuario = usuarioCounter.findById("usuariosId");
			nuevoUsuario.setId(Integer.toString(secuenciaUsuario.get().getValor()));
			userRepository.save(nuevoUsuario);
			secuenciaUsuario.get().setValor(secuenciaUsuario.get().getValor()+1);
			usuarioCounter.save(secuenciaUsuario.get());
			
		}catch(Exception e) {
			log.error(e.toString());
			return new ResponseEntity<ResponseTO>(response, headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseTO>(response, headers, HttpStatus.OK);
		
	}
	
	
}
