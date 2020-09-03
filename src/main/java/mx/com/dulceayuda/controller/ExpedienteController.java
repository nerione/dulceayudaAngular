package mx.com.dulceayuda.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.converter.ExpedienteConverter;
import mx.com.dulceayuda.entity.ExpedienteEntity;
import mx.com.dulceayuda.model.Expediente;
import mx.com.dulceayuda.repository.ExpedienteRepository;
import mx.com.dulceayuda.response.ResponseExpedienteTO;
import mx.com.dulceayuda.response.ResponseTO;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class ExpedienteController {
	
	@Autowired
	private ExpedienteRepository expedienteRepo;
	
	//Abrir un nuevo expediente para usuario/Paciente
	@PostMapping(path = "/expediente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setExpediente(@RequestParam(required = true, name = "expediente") Expediente expediente){
		
		HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					
		try {
			
			expedienteRepo.save(ExpedienteConverter.modelToEntity(expediente));
			return new ResponseEntity<ResponseTO>(HttpStatus.CREATED);
			
		}catch(Exception e) {
			log.error("Occurrio un error al intentar aperturar expediente para el usuario {}", e.toString());
			return new ResponseEntity<ResponseTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}			
		
		
	}
	
	//Recuperar expediente del usuario
	@GetMapping(path = {"/expediente/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mundo(@PathParam(value = "idPaciente") String idPaciente) {
		
		log.info("Recuperando expediente. Usuario : {}", idPaciente);
		HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			
			ExpedienteEntity expedienteEntity = expedienteRepo.findByIdPaciente(idPaciente);
			Expediente expediente = ExpedienteConverter.entityToModel(expedienteEntity);
			
			return new ResponseEntity<ResponseTO>(new ResponseExpedienteTO("OK", null, "correcto", expediente), HttpStatus.OK);
			
		}catch(Exception e) {
			log.error("Ocurrio un error al intentar recuperar su expediente del usuario {}", idPaciente);
			return new ResponseEntity<ResponseTO>(new ResponseExpedienteTO("ERROR", null, "No se pudo recuperar su expediente.", null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		

	}

}
