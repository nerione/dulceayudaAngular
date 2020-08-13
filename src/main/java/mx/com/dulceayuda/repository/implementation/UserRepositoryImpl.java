package mx.com.dulceayuda.repository.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mx.com.dulceayuda.converter.Converter;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.exceptions.AltaUsuarioException;
import mx.com.dulceayuda.model.Usuario;
import mx.com.dulceayuda.repository.UserRepository;


@Slf4j
@Repository
public class UserRepositoryImpl /*implements UserRepository*/{
	
	/*@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public UsuarioEntity getUsuarioById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		UsuarioEntity _usuario =  mongoTemplate.findOne(query, UsuarioEntity.class);
		return _usuario;
	}


	@Override
	public UsuarioEntity altaUsuairo(Usuario usuario){
		
		log.info("iniciando alta de nuevo usuario....... {} " , usuario.toString());
		//si el usuario no pudo ser registrado
		return mongoTemplate.save(Converter.userModelToEntity(usuario));
		
	}


	@Override
	public UsuarioEntity getUsuarioByName(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		return mongoTemplate.findOne(query, UsuarioEntity.class);
	}*/
	

}
