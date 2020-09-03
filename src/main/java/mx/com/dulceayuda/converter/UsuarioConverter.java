package mx.com.dulceayuda.converter;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.model.Usuario;
import mx.com.dulceayuda.utilities.Utilerias;

@Slf4j
public class UsuarioConverter {
	
	
	//Convertir Entidad a Model
	public static Usuario userEntityToModel(UsuarioEntity usuarioEntity) {
		
		return new Usuario(usuarioEntity.getId(), 
				usuarioEntity.getNombre(), 
				usuarioEntity.getApellidoPaterno(), 
				usuarioEntity.getApellidoMaterno(), 
				usuarioEntity.getEdad(), 
				usuarioEntity.getEmail(), 
				usuarioEntity.getTelefono(), 
				usuarioEntity.getUserName(), 
				usuarioEntity.getUserPass(),
				usuarioEntity.isEnabled(),
				usuarioEntity.getUserRole(), 
				usuarioEntity.getEspecialidad(), 
				usuarioEntity.getCodigoReferido(), 
				usuarioEntity.isPrimeraCitaGratis(), 
				usuarioEntity.getMunicipio(), 
				usuarioEntity.getEstado(), 
				usuarioEntity.getIdProfesional());
	}
	
	
	//Convertir Model a Entidad 
	public static UsuarioEntity userModelToEntity(Usuario usuario) {
		
		String _codigoReferido = usuario.getNombre().concat(Utilerias.generateOTP());
		
		return new UsuarioEntity(usuario.getId(), 
				usuario.getNombre(), 
				usuario.getApellidoPaterno(), 
				usuario.getApellidoMaterno(), 
				usuario.isEnabled(), 
				usuario.getEdad(), 
				usuario.getEmail(), 
				usuario.getTelefono(), 
				usuario.getUserName(), 
				usuario.getUserPass(), 
				usuario.getUserRole(), 
				usuario.getEspecialidad(), 
				_codigoReferido.replace(" ", ""),
				usuario.isPrimeraCitaGratis(), 
				usuario.getMunicipio(), 
				usuario.getEstado(), 
				usuario.getIdProfesional());
	}
	

}
