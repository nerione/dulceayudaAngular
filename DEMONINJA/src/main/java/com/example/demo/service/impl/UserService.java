package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("USUARIO NO EXISTE");
		}
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}
	
	private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), 
				true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
		
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		userRoles.forEach( rol -> auths.add(new SimpleGrantedAuthority(rol.getRole())));
		return new ArrayList<GrantedAuthority>(auths);
	}

}
