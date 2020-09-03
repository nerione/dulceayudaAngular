package mx.com.dulceayuda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.dulceayuda.entity.ExpedienteEntity;

@Repository
public interface ExpedienteRepository extends MongoRepository<ExpedienteEntity, String> {
	
	public abstract ExpedienteEntity findByIdPaciente(String idPaciente);
	

}
