package mx.com.dulceayuda.entity;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "citas")
public class CitaEntity implements Serializable {

	private static final long serialVersionUID = 79294780906079891L;

	@Id
	private String id;
	
	private String idPaciente;
	private LocalDate fecha;
	private String estatus;

}
