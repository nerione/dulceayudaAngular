package com.example.demo.threads;


public class PigFactory {
	
	private int id;
	private String nombre;
	private String eat;
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEat() {
		return eat;
	}



	public void setEat(String eat) {
		this.eat = eat;
	}



	@Override
	public String toString() {
		return "PigFactory [id=" + id + ", nombre=" + nombre + ", eat=" + eat + "]";
	}
	
	

}
