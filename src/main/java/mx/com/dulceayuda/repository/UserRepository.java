package mx.com.dulceayuda.repository;

import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.exceptions.AltaUsuarioException;


public interface UserRepository{
	
	public abstract UsuarioEntity getUsuarioById(String id);
	
	public abstract UsuarioEntity getUsuarioByName(String userName);
	
	public abstract UsuarioEntity altaUsuairo(UsuarioEntity usuario) throws AltaUsuarioException;

}
