package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public User getUserByUsername(String username) {
		User user = new User();
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		user = mongoTemplate.findOne(query, User.class);
		return user;
	}
	
//	@Override
//	public User getUserByUsername(String username) {
//		// TODO Auto-generated method stub
//		User user = new User();
//		Query query = new Query();
//		query.addCriteria(Criteria.where("username").is(username));
//		user = mongoTemplate.findOne(query, User.class);
//		return user;
//	
//	}
//
//	@Override
//	public User setNewUser(User user) {
//		
//		User userNew = new User();
//		Set<UserRole> userRole = new HashSet<UserRole>();
//		
//		userRole.add(new UserRole(Integer.toString(1), "el mero chido"));
//		userRole.add(new UserRole(Integer.toString(2), "mortal"));
//		userRole.add(new UserRole(Integer.toString(3), "dios"));
//		
//		userNew.setEnabled(true);
//		userNew.setId(Integer.toString(1));
//		userNew.setPassword("nerione");
//		userNew.setUsername("nerione");
//		userNew.setUserRole(userRole);
//		
//		
//		userNew = mongoTemplate.save(userNew);
//		return userNew;
//	}
	

}
