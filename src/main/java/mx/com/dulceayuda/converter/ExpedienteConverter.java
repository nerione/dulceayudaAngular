package mx.com.dulceayuda.converter;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.entity.ExpedienteEntity;
import mx.com.dulceayuda.model.Expediente;

@Slf4j
public class ExpedienteConverter {
	
	
	public static ExpedienteEntity modelToEntity(Expediente model) {
		log.info("Conversion de ExpedienteEntity a Model");
		return new ExpedienteEntity(model.getId(), model.getIdPaciente(), model.getNotas(), model.getSemblanzaPaciente(), model.getFechaApertura());
	}
	
	
	public static Expediente entityToModel(ExpedienteEntity entity) {
		log.info("Conversion de ExpedienteModel a Entity");
		return new Expediente(entity.getId(), entity.getIdPaciente(), entity.getNotas(), entity.getSemblanzaPaciente(), entity.getFechaApertura());
	}
	

}
