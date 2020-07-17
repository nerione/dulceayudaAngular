package com.example.demo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SMSRequest implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String message;
	@JsonProperty
	private String numbers;
	@JsonProperty
	private int country_code;
	
	//propiedad para enviar mensajes en modo de prueba para no gastar credito sms.
	private int sandbox;
	
		

	
	@Override
	public String toString() {
		return "SMSRequest [message=" + message + ", numbers=" + numbers + ", country_code=" + country_code + ", sandbox="
				+ sandbox + "]";
	}





	public int getSandbox() {
		return sandbox;
	}





	public void setSandbox(int sandbox) {
		this.sandbox = sandbox;
	}





	public String getMessage() {
		return message;
	}





	public void setMessage(String message) {
		this.message = message;
	}





	public String getNumbers() {
		return numbers;
	}





	public void setNumbers(String numbers) {
		this.numbers = numbers;
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
	

}
