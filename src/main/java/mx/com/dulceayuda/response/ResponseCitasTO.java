package mx.com.dulceayuda.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.dulceayuda.entity.CitaEntity;

@Data
@AllArgsConstructor()
public class ResponseCitasTO extends ResponseTO{
	
	private List<CitaEntity> citas;
	
	@Builder
	public ResponseCitasTO(String codigoRespuesta, List<String> errores, String mensaje, List<CitaEntity> citas) {
		super(codigoRespuesta, errores, mensaje);
		this.citas = citas;
	}
	

}
