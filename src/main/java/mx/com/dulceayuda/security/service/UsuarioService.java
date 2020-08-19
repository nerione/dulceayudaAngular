package mx.com.dulceayuda.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.com.dulceayuda.entity.UserRole;
import mx.com.dulceayuda.entity.UsuarioEntity;
import mx.com.dulceayuda.repository.UserRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		UsuarioEntity usuario = userRepository.findByUserName(userName);// getUsuarioByName(userName);
		
		if(usuario == null)
			throw new UsernameNotFoundException("El usuario "+userName+" no fue encontrado");
		
		List<GrantedAuthority> authorities = buildAuthorities(usuario.getUserRole());
		
		return buildUser(usuario, authorities);
	}

	//Se contruye un usuario de Spring Security
	private org.springframework.security.core.userdetails.User buildUser(UsuarioEntity usuario, List<GrantedAuthority> authorities) {
		return new User(usuario.getUserName(), usuario.getUserPass(), usuario.isEnabled(), 
						true, true, true, authorities);
	}
	
	
	//Se contruye la lista de GrantedAuthorities a partir de los roles del usuario.
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles) {
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		userRoles.forEach(rol -> auths.add(new SimpleGrantedAuthority(rol.getRole())));
		return new ArrayList<GrantedAuthority>(auths);
	}
	
	
	


}
