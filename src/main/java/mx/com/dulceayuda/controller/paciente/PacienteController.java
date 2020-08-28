package mx.com.dulceayuda.controller.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.converter.CitaConverter;
import mx.com.dulceayuda.entity.CitaEntity;
import mx.com.dulceayuda.model.Cita;
import mx.com.dulceayuda.repository.CitaRepository;
import mx.com.dulceayuda.response.ResponseCitasTO;
import mx.com.dulceayuda.response.ResponseTO;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/paciente")
public class PacienteController {
	
	@Autowired
	private CitaRepository citaRepository;
	
	
	@GetMapping(path = {"/citas/{id}","/citas"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCitasTO> getGenerales(@PathVariable(required = false, name = "id") String id){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		List<CitaEntity> citas = null;
		
		try {
			
			log.info("Se inicia proceso de recuperacion de las citas del usuario");
			if(id != null) {
				log.info("Se filtran citas para el usuario {}", id);
				citas = citaRepository.findByIdPaciente(id);
				citas.stream().forEach(cita -> log.info("Cita :{}", cita.toString()));
				
			}else {
				log.info("Se recuperaran el total de las citas en l BD");
				citas = citaRepository.findAll();
				citas.parallelStream().forEach(c -> log.info("Citas encontradas {}" ,c.toString()));
			}
			
		}catch(Exception e ) {
			log.error("Error al intentar recuperar el calendario de citas {} ", e.getMessage());
		}
		return ResponseEntity.ok().headers(headers).body(new ResponseCitasTO("OK", null, "Citas recuperadas exitosamente", citas));
	}
	
	
	@PostMapping(path = "/citas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> citas(@RequestBody Cita cita){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<ResponseTO> response = null;
		
		try {
			
			log.info("idUser : {} ", cita.toString());
			CitaEntity citaEntity = CitaConverter.citaModelToEntity(cita);
			citaRepository.save(citaEntity);
			
			log.info("Consulta de cita para usuario 1 {} ", citaRepository.findById("1"));
			
			response = ResponseEntity.ok().body(new ResponseTO("EXITO", null, "La cita fue agendada exitosamente."));
			
		}catch (Exception e) {
			log.error("Ocurrio un problema al intentar generar una cita para el usuario {} {}" , cita.getIdPaciente(), e.getMessage());
			response = ResponseEntity.ok().body(new ResponseTO("Error", null, "La cita no pudo agendarse, intente más tarde o contactenos por otro medio"));
		}
		
		return response;
	
	}
	
	/*@GetMapping(path = "/")
	
	@PostMapping(path = "/comentario")
	
	@GetMapping(path = "/expendiente")*/

}