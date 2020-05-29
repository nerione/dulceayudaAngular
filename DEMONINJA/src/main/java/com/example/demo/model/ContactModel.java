package com.example.demo.model;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

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
	private String fechaRegistro;
	
	private String file;
	
	@Transient
	private String fechaRegistroFront;
	
	private String datoInutil;
	
	public ContactModel() {}
	
	public ContactModel(String id, String firstName, String lastName, String telephone, String city, String datoInutil, String fechaRegistro, String fechRegistroFront, String file) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.city = city;
		this.datoInutil = datoInutil;
		this.fechaRegistro = fechaRegistro;
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

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone="
				+ telephone + ", city=" + city + ", fechaRegistro=" + fechaRegistro + ", file=" + file
				+ ", fechaRegistroFront=" + fechaRegistroFront + ", datoInutil=" + datoInutil + "]";
	}

	@PostConstruct
	private void init() {
		System.out.println("EL BEAN CONTACTMODEL YA FUE CARGADO AL CONTENEDOR...*************************************************************************");
	}
	
	
	

}
