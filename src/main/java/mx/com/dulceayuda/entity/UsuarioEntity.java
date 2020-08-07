package mx.com.dulceayuda.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class UsuarioEntity implements Serializable {
	
	
	private static final long serialVersionUID = -2823285207300947655L;

	@Id
	private String id;
	
	@NotEmpty(message = " es requerido.")
	private String nombre = "nerione";
	@NotEmpty(message = " es requerido.")
	private String apellidoPaterno;
	@NotEmpty(message = " es requerido.")
	private String apellidoMaterno;
	private boolean enabled;
	private int edad;
	private String email;
	private String telefono;
	//nombre de usuario
	private String userName;
	//llave de acceso
	private String userPass;
	private Set<UserRole> userRole = new HashSet<>();
	
	
	//Atributos exclusivos para usuarios Psicologos
	private String especialidad;
	
	//Atributos exclusivos para usuarios Pacientes
	private String codigoReferido;
	private boolean primeraCitaGratis;
	private String municipio;
	private String estado;
	private String idProfesional;

}
