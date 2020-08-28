package mx.com.dulceayuda.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "citas")
public class CitaEntity implements Serializable {

	private static final long serialVersionUID = 79294780906079891L;

	@Id
	private String id;
	
	private String idPaciente;
	private Date fecha;
	private int hora;
	private String estatus;

}
