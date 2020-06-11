package com.example.demo.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.service.UploadImagenService;

//Para despliegues en ambientes productivos, es necesario agregar a la linea de abajo, el dominio, ruta o ip donde se encuentre desplegado el front. 
//El * indica que acepte peticiones de donde sea. Por ahora esta bien pero no es recomendable
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping(path = "/api")
public class ImagenController {
	
	private static final Log log = LogFactory.getLog(ImagenController.class);
	
	@Autowired
	private UploadImagenService uploadImagenService;
	
	@Autowired
	ContactRepository contactRepository;

	                                 //{fileName:.+} ---> Expresion regular que indica que el parametro va a contener un punto y una extension		
	@GetMapping(path = "/contacts/file/{fileName:.+}")
	public ResponseEntity<?> getFile(@PathVariable(name = "fileName", required = false) String fileName){
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "image/png; charset=utf-8");
		ResponseEntity<byte[]> response = null;

		try {
			byte[] image = uploadImagenService.cargarImagen(fileName);
			headers.setContentLength(image.length);
			response = ResponseEntity.status(HttpStatus.OK).headers(headers).body(image);
		}catch(Exception e){
			log.error("Problemas al recuperar la imagen " + e.getCause().getMessage());
		}
		return response;
	}
	
	//Recurso protegido por springSecurity mediante anotacion @Secured
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping(path = "/contacts/upload")
	public ResponseEntity<?> upload(@RequestParam(name = "archivo", required = true) MultipartFile archivo, @RequestParam String id){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		Map<String, Object> response = new HashMap<String, Object>();
		
		try {
			//Obtenemos el contacto
			Contact _contact = contactRepository.getContactById(id);
						
			if(!archivo.isEmpty()) {
				
				String fotoVigente = _contact.getFile();
				
				if(fotoVigente != null)
					log.info("SE ELIMINO IMAGEN ANTERIOR ?" + Files.deleteIfExists((Paths.get("fotos").resolve(_contact.getFile()).toAbsolutePath()))); ;
				
				String nombre_archivo = uploadImagenService.copiarImage(archivo);
				//guardamos el nombre de la foto por ahora
				_contact.setFile(nombre_archivo);
				//actualizamos el contacto
				contactRepository.updateContact(_contact);
				
				response.put("cliente", _contact);
				response.put("mensaje", "Se ha subido foto"+ nombre_archivo +"correctamente");
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
			
		}catch(Exception e) {
			log.error("Problemas al intentar subir archivo...");
			response.put("error" , e.getCause().getMessage());
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}


}
