package com.example.demo.model;

import java.io.Serializable;

public class SMSResponse implements Serializable{
	
	private String codigoRespuesta;
	private String mensaje;
	
	
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	@Override
	public String toString() {
		return "SMSResponse [codigoRespuesta=" + codigoRespuesta + ", mensaje=" + mensaje + "]";
	}	
	

}
