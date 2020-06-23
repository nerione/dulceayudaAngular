package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

public class SMSRequest implements Serializable{
	
	private String message;
	
	private String number;
	
	private int country_code;
	
	
	

	
	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}





	public String getNumber() {
		return number;
	}





	public void setNumber(String number) {
		this.number = number;
	}





	public int getCountry_code() {
		return country_code;
	}





	public void setCountry_code(int country_code) {
		this.country_code = country_code;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@Override
	public String toString() {
		return "SMSRequest [message=" + message + ", number=" + number + ", country_code=" + country_code + "]";
	}





	private static final long serialVersionUID = 2817859182963556361L;
	

}
