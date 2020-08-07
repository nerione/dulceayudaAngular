package mx.com.dulceayuda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Nota {
	
	private String id;
	private String idTipoNota;
	private String descripcion;

}
