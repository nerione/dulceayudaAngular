package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logg")
public class Log {
	
	@Id
	private String id;
	private String dateTimeString;
	private String details;
	private String userName;
	private String url;
	
	public Log() {}

	public Log(String dateTimeString, String details, String userName, String url) {
		super();
		this.dateTimeString = dateTimeString;
		this.details = details;
		this.userName = userName;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTimeString() {
		return dateTimeString;
	}

	public void setDate(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", dateTimeString=" + dateTimeString + ", details=" + details + ", userName=" + userName + ", url=" + url
				+ "]";
	}
	

}
