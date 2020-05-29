package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contacts")
public class Contact implements Serializable{
	
	private static final long serialVersionUID = -5505267657214298200L;
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String telephone;
	private String city;
	private String fechaRegistro;
	private String file;
	@Transient
	private String fechaRegistroFront;
	
	//Esta anotacion exluye este campo al momento de persistir en base de datos.
	@Transient
	private String datoInutil;
	
	public Contact() {}


	public Contact(String idContact, @NotNull String firstName, String lastName, String telephone, String city, String datoInutil, String fechRegistroFront, String file) {
		super();
		this.id = idContact;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.city = city;
		this.datoInutil = datoInutil;
		this.fechaRegistroFront = fechRegistroFront;
		this.file = file;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDatoInutil() {
		return datoInutil;
	}



	public void setDatoInutil(String datoInutil) {
		this.datoInutil = datoInutil;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	

	public String getFechaRegistroFront() {
		return fechaRegistroFront;
	}


	public void setFechaRegistroFront(String fechaRegistroFront) {
		this.fechaRegistroFront = fechaRegistroFront;
	}
	
	


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone=" + telephone
				+ ", city=" + city + ", fechaRegistro=" + fechaRegistro + ", file=" + file + ", fechaRegistroFront="
				+ fechaRegistroFront + ", datoInutil=" + datoInutil + "]";
	}


	
	
	
}
