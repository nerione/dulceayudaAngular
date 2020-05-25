package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository{
	
	public abstract User getUserByUsername(String username);	

}
