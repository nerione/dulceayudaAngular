package com.example.demo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.UploadImagenService;


@Service
public class UploadImagenServiceImpl implements UploadImagenService{
	
	@Autowired
	MongoTemplate montoTemplate;
	
	private static final String FOLDER_IMAGES= "fotos";

	@Override
	public byte[] cargarImagen(String fileName) throws IOException {
		
		Path ruta = getPath(fileName);

		if(! Files.exists(ruta, java.nio.file.LinkOption.NOFOLLOW_LINKS))
			ruta = Paths.get("src").resolve("main/resources/static/images/noFotoUser.png").toAbsolutePath();				

		byte[] imagen = Files.readAllBytes(ruta);
		
		return imagen;
	}

	@Override
	public String copiarImage(MultipartFile archivo) throws IOException {
		
		String nombre_archivo = UUID.randomUUID()+archivo.getOriginalFilename();
		Path ruta = Paths.get("fotos").resolve(nombre_archivo).toAbsolutePath();
		Files.copy(archivo.getInputStream(), ruta);
		
		return nombre_archivo;
	}

	@Override
	public boolean borrarImagen(String file) throws IOException {
		return Files.deleteIfExists(Paths.get("fotos").resolve(file).toAbsolutePath());	
	}

	@Override
	public Path getPath(String nombreFoto) {
		// TODO Auto-generated method stub
		return Paths.get(FOLDER_IMAGES).resolve(nombreFoto).toAbsolutePath();
	}

	

}
