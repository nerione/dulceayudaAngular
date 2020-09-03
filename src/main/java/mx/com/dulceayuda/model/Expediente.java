package mx.com.dulceayuda.model;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.dulceayuda.entity.Nota;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expediente {
	
	private String id;
	private String idPaciente;
	private Set<Nota> notas;
	private String semblanzaPaciente;
	private Date fechaApertura;

}
