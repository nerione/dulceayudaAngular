package mx.com.dulceayuda.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.dulceayuda.entity.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	private String id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int edad;
	private String email;
	private String telefono;
	private String uname;
	private String upass;
	private Set<UserRole> userRole = new HashSet<>();
	private String especialidad;
	private String codigoReferido;
	private boolean primeraCitaGratis;
	private String municipio;
	private String estado;
	private String idProfesional;	
	

}
