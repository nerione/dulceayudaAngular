package mx.com.dulceayuda.converter;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.entity.CitaEntity;
import mx.com.dulceayuda.model.Cita;

@Slf4j
public class CitaConverter {
	
	
	
	public static Cita citaEntityToModel(CitaEntity citaEntity) {
		log.info("Transformando {} ENTIDAD A MODELO", citaEntity.getClass().getName());
		return new Cita(citaEntity.getId(), citaEntity.getIdPaciente(), citaEntity.getFecha(), citaEntity.getHora(), citaEntity.getEstatus());
	}
	
	
	
	public static CitaEntity citaModelToEntity(Cita cita) {
		log.info("Transformando  {} MODELO A ENTIDAD", cita.getClass().getName());
		return new CitaEntity(cita.getId(), cita.getIdPaciente(), cita.getFecha(), cita.getHora(), cita.getEstatus());
	}

}
