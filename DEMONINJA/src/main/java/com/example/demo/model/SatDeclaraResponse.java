package com.example.demo.model;

import java.util.List;

public class SatDeclaraResponse {
	
	private String respuesta;
	private boolean error;
	private List<Factura> facturasEmitidas;
	private List<Factura> facturasRecibidas;
	private String subTotalEmitidas;
	private String totalEmitidas;
	private String subTotalRecibidas;
	private String totalRecibidas;
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	
	public List<Factura> getFacturasEmitidas() {
		return facturasEmitidas;
	}
	public void setFacturasEmitidas(List<Factura> facturasEmitidas) {
		this.facturasEmitidas = facturasEmitidas;
	}
	public List<Factura> getFacturasRecibidas() {
		return facturasRecibidas;
	}
	public void setFacturasRecibidas(List<Factura> facturasRecibidas) {
		this.facturasRecibidas = facturasRecibidas;
	}
	public String getSubTotalEmitidas() {
		return subTotalEmitidas;
	}
	public void setSubTotalEmitidas(String subTotalEmitidas) {
		this.subTotalEmitidas = subTotalEmitidas;
	}
	public String getTotalEmitidas() {
		return totalEmitidas;
	}
	public void setTotalEmitidas(String totalEmitidas) {
		this.totalEmitidas = totalEmitidas;
	}
	public String getSubTotalRecibidas() {
		return subTotalRecibidas;
	}
	public void setSubTotalRecibidas(String subTotalRecibidas) {
		this.subTotalRecibidas = subTotalRecibidas;
	}
	public String getTotalRecibidas() {
		return totalRecibidas;
	}
	public void setTotalRecibidas(String totalRecibidas) {
		this.totalRecibidas = totalRecibidas;
	}
	@Override
	public String toString() {
		return "SatDeclaraResponse [respuesta=" + respuesta + ", error=" + error + ", facturasEmitidas="
				+ facturasEmitidas + ", facturasRecibidas=" + facturasRecibidas + ", subTotalEmitidas="
				+ subTotalEmitidas + ", totalEmitidas=" + totalEmitidas + ", subTotalRecibidas=" + subTotalRecibidas
				+ ", totalRecibidas=" + totalRecibidas + "]";
	}
	
	
	
	
	

}
