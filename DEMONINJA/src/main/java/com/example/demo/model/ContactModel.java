package com.example.demo.model;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactModel {
	
	private String id;
	
	@NotEmpty(message = "Campo obligatorio")
	@Size(max = 10, message = "Campo nombre excede los 10 caracteres permitidos")
	private String firstName;
	
	@NotEmpty(message = "Campo obligatorio")
	@Size(max = 10, message = "Campo apellido excede los 10 caracteres permitidos")
	private String lastName;
	
	@NotEmpty(message = "Campo obligatorio")
	@Size(min = 10, message = "Campo telefono debe ser de 10 digitos")
	private String telephone;
	
	@NotEmpty(message = "Campo obligatorio")
	@Size(min = 3, message = "Campo ciudad debe ser de 3 caracteres")
	private String city;
	
	@NotNull(message =  "Campo Fecha es obligatorio")
	private Date fechaRegistro;
	
	private String datoInutil;
	
	public ContactModel() {}
	
	public ContactModel(String id, String firstName, String lastName, String telephone, String city, String datoInutil, Date fechaRegistro) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.city = city;
		this.datoInutil = datoInutil;
		this.fechaRegistro = fechaRegistro;
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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone="
				+ telephone + ", city=" + city + ", fechaRegistro=" + fechaRegistro + ", datoInutil=" + datoInutil
				+ "]";
	}
	
	
	@PostConstruct
	private void init() {
		System.out.println("EL BEAN CONTACTMODEL YA FUE CARGADO AL CONTENEDOR...*************************************************************************");
	}
	
	
	

}
