package mx.com.dulceayuda.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "expedientes")
public class ExpedienteEntity implements Serializable {
	

	private static final long serialVersionUID = 3074995885663539014L;

	@Id
	private String id;
	
	private String idPaciente;
	private Set<Nota> notas;
	private String semblanzaPaciente;
	private LocalDate fechaApertura;
	

}
