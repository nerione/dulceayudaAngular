package mx.com.dulceayuda.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
	
	private String id;
	private String idPaciente;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private int hora;
	private String estatus;	
	
	/*private Date setFecha(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.format(fecha);
		return format.format(fecha);
	}*/

}
