package mx.com.dulceayuda.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTO {
	
	@JsonIgnoreProperties({"codigoRespuesta"})
	private String codigoRespuesta;
	
	@JsonProperty
	private List<String> errores;
	
	private String mensaje;
	

}
