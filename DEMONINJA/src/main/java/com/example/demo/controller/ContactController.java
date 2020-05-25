package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.example.demo.converter.ContactConverter;
import com.example.demo.entity.Contact;
import com.example.demo.model.ContactModel;
import com.example.demo.repository.ContactRepository;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/api")
public class ContactController {
	
	private static final Log log = LogFactory.getLog(ContactController.class);
	
	@Autowired
	private ContactRepository contactService;
	
	@Autowired
	private ContactConverter contactConverter;
	
	//API PARA OPERACIONES BASICAS SOBRE UN CONTACTO
	
	//Create
	/*
	 * @PostMapping(path = {"/contacts"}, consumes =
	 * MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * public ResponseEntity<List<ContactModel>> saveContacts(@RequestBody
	 * List<ContactModel> contactModelListRequest){
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON);
	 * ResponseEntity<List<ContactModel>> response = null;
	 * 
	 * try { log.info("EJECUTANDO OPERACION DE CREACION"); List<Contact>
	 * contactList= new ArrayList<Contact>(); contactList =
	 * contactModelListRequest.parallelStream().map(contactItem ->
	 * contactConverter.contacModel2Contact(contactItem)).collect(Collectors.toList(
	 * )); contactList = (List<Contact>) contactService.createContact(contactList);
	 * response =
	 * ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(null);
	 * 
	 * }catch (Exception e) {
	 * log.error("Ocurrio un problema el intentar salvar los Contactos en BD");
	 * response =
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).
	 * build(); }
	 * 
	 * return response;
	 * 
	 * }
	 */
	
	//Create
		@PostMapping(path = {"/contacts"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> saveContacts(@Valid @RequestBody ContactModel contactModelRequest, BindingResult result){
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<ContactModel> response = null;
			Map<String, Object> horrores = new HashMap();
			
			 List<String> errores = result.getFieldErrors().parallelStream().map( error -> error.getField() + error.getDefaultMessage()).collect(Collectors.toList());
			 if(errores != null && errores.size() != 0) {
				horrores.put("errores", errores);
				return new ResponseEntity<Map<String, Object>>( horrores, headers, HttpStatus.BAD_REQUEST);
			 }
			try {
				log.info("EJECUTANDO OPERACION DE CREACION");
				Contact contact= new Contact();
				ContactModel cm = new ContactModel();
				contact = contactConverter.contacModel2Contact(contactModelRequest);
				contact = contactService.createContact(contact);
				cm = contactConverter.contac2ContactModel(contact);
				response =  ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(cm);
							
			}catch (Exception e) {
				log.error("Ocurrio un problema el intentar salvar los Contactos en BD");
				response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).build();
			}
			
			return response;

		}
		
		
	//R
	@GetMapping(path = {"/contacts/{id}", "/contacts"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> showContacts(@PathVariable(name = "id", required = false) String id) {		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		ResponseEntity<?> response = null;
		Map<String, String> horror = new HashMap();
		
		
		//Page<Contact> contacts = contactService.findAll(new PageRequest)
		//Paginacion con spring data
		//contactService.findAll(pageable)
		
		Page<Contact> paginados = contactService.findAll(PageRequest.of(3, 3));
		
		
		try {
			log.info("EJECUTANDO OPERACION DE CONSULTA");
			if(id != null) {
				Contact c = new Contact();
				ContactModel cm = new ContactModel();
				c = contactService.getContactById(id);
				if(c != null) {
					cm = contactConverter.contac2ContactModel(c);
				}	
				response = response.status(c != null ? HttpStatus.OK : HttpStatus.NOT_FOUND).headers(httpHeaders).body(cm);
				
			}else {
				//Recuperamos la lista completa de contactos
				List<Contact> listaContactos = contactService.getAllContacts();
				List<ContactModel> contacModelLista = listaContactos.parallelStream().map(c-> contactConverter.contac2ContactModel(c)).collect(Collectors.toList());
				response = response.ok().headers(httpHeaders).body(paginados);
			}		

		}catch (Exception e) {
			log.error("PROBLEMAS AL RECUPERAR LA LISTA DE CONTACTOS");	
		}
		
		return response;
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
	//Update de entidades Contacto en BD
	@PutMapping(path = {"/contacts/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateContact(@Valid BindingResult result, @PathVariable(name = "id", required = true) String id, @RequestBody ContactModel contactModelRequest){
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		List<String> errores = result.getFieldErrors().parallelStream().map(error -> error.getField() + " " + error.getDefaultMessage()).collect(Collectors.toList());
		
		if(errores != null)
			return new ResponseEntity<List<String>>(errores, httpHeaders, HttpStatus.BAD_REQUEST);
		try {
			log.info("EJECUTANDO OPERACION DE ACTUALIZACION");
			Contact cm = contactConverter.contacModel2Contact(contactModelRequest);
			contactService.updateContact(cm);
			log.info("El contacto fue actualizado correctamente");
			return new ResponseEntity<Contact>(cm, httpHeaders, HttpStatus.OK);
			
		}catch (Exception e) {
			log.error("Ocurrio un problema al intentar actualizar documento " + e.getCause());
			return new ResponseEntity<Contact>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	
	
	//Delete de entidades Contacto en BD
	@DeleteMapping(path = {"/contacts/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteContact(@PathVariable(name = "id", required = true) String id){
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Map<String, String> horror = new HashMap();
		try {
			log.info("EJECUTANDO OPERACION DE BORRADO");
			long itemsEliminados = contactService.deleteContact(id);
			if(itemsEliminados > 0) {
				log.info( itemsEliminados + "Contactos eliminados de manera correcta");
				return new ResponseEntity<Contact>(null, httpHeaders, HttpStatus.OK);
				}
			else {
				log.warn("El contacto con Id " + id + " no fue encontrado");
				horror.put("mensaje", "El contacto ".concat(id).concat(" no fue localizado"));
				return new ResponseEntity<Map<String, String>>(horror, httpHeaders, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			log.error("Ocurrio un problema al intentar eliminar Contacto con ID " + id);
			return new ResponseEntity<Contact>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	

}
