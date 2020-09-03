package mx.com.dulceayuda.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expedientes")
public class ExpedienteEntity implements Serializable {
	

	private static final long serialVersionUID = 3074995885663539014L;

	@Id
	private String id;
	
	private String idPaciente;
	private Set<Nota> notas;
	private String semblanzaPaciente;
	private Date fechaApertura;
	

}
