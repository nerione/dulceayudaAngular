package mx.com.dulceayuda.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.dulceayuda.model.Expediente;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExpedienteTO extends ResponseTO{
	
	private Expediente expediente;
	
	@Builder
	public ResponseExpedienteTO(String codigoRespuesta, List<String> errores, String mensaje, Expediente expediente) {
		super(codigoRespuesta, errores, mensaje);
		this.expediente = expediente;
	}

}
