package mx.com.dulceayuda.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.dulceayuda.entity.CitaEntity;


@Repository
public interface CitaRepository extends MongoRepository<CitaEntity, String>{
	
	public abstract List<CitaEntity> findByIdPaciente(String id);

}
