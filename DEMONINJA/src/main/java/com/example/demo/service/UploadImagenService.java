package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface UploadImagenService {
	
	public abstract byte[] cargarImagen(String fileName) throws IOException;
	
	public abstract String copiarImage(MultipartFile multipartFile)throws IOException;
	
	public abstract boolean borrarImagen(String file)throws IOException;
	
	public abstract Path getPath(String nombreFoto);

}
