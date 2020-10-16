package mx.com.dulceayuda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private int edad;
	private String email;
	private String telefono;
	private String userName;
	private String userPass;
	private String municipio;
	private String estado;	

}
