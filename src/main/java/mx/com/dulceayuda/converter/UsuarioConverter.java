package mx.com.dulceayuda.converter;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.model.Usuario;
import mx.com.dulceayuda.utilities.Utilerias;

@Slf4j
public class UsuarioConverter {
	
	
	//Convertir Entidad a Model
	public static Usuario userEntityToModel(UsuarioEntity usuarioEntity) {
		log.info("Transformando entidad a modelo");		
		return new Usuario( 
				usuarioEntity.getNombre(), 
				usuarioEntity.getApellidoPaterno(), 
				usuarioEntity.getApellidoMaterno(), 
				usuarioEntity.getEdad(), 
				usuarioEntity.getEmail(), 
				usuarioEntity.getTelefono(), 
				usuarioEntity.getEspecialidad(), 
				usuarioEntity.getCodigoReferido(), 
				usuarioEntity.getMunicipio(), 
				usuarioEntity.getEstado()); 
	}
	
	
	//Convertir Model a Entidad 
	public static UsuarioEntity userModelToEntity(Usuario usuario) {
		log.info("Transformando modelo a entidad");		
		String _codigoReferido = usuario.getNombre().concat(Utilerias.generateOTP());
		
		//return new UsuarioEntity(id, nombre, apellidoPaterno, apellidoMaterno, enabled, edad, email, telefono, userName, userPass, userRole, especialidad, codigoReferido, primeraCitaGratis, municipio, estado, idProfesional)
		return new UsuarioEntity("", usuario.getNombre(), usuario.getApellidoPaterno(), usuario.getApellidoMaterno(), true, usuario.getEdad(), usuario.getEmail(),
				usuario.getTelefono(), usuario.getUserName(), usuario.getUserPass(), null, null, _codigoReferido.replace(" ", " "), false, usuario.getMunicipio(),
				usuario.getEstado(), null);
	

	}
}
