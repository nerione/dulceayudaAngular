package mx.com.dulceayuda.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.dulceayuda.entity.UsuarioEntity;


@Repository
public interface UserRepository extends MongoRepository<UsuarioEntity, String>{
	
	public abstract UsuarioEntity findByNombre(String nombre);

}
