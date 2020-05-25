package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Log;

@Repository
public interface LogRepository {
	
	public abstract Log saveLog(Log log);

}
