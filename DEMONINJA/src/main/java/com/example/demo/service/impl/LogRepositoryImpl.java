package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Log;
import com.example.demo.repository.LogRepository;

@Service
public class LogRepositoryImpl implements LogRepository{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Log saveLog(Log log) {
		return mongoTemplate.save(log);
	}
	
	

}
