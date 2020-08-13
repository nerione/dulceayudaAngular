package mx.com.dulceayuda.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.com.dulceayuda.entity.UsuarioEntity;


@Repository
public interface UserRepository extends MongoRepository<UsuarioEntity, String>{
	
	public abstract UsuarioEntity findByNombre(String nombre);
	
	//public abstract UsuarioEntity getUsuarioEntityByName(String userName);
	
	//public abstract UsuarioEntity altaUsuairo(Usuario usuario);

}
