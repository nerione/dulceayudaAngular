package mx.com.dulceayuda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.dulceayuda.entity.UsuarioSequenceEntity;

@Repository
public interface UsuarioCounter extends MongoRepository<UsuarioSequenceEntity, String>{
	
	

}
