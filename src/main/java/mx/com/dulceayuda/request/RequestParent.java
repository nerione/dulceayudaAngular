package mx.com.dulceayuda.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestParent {
	
	private String codigoRespuesta;
	private boolean error;
	private String mensaje;

}
