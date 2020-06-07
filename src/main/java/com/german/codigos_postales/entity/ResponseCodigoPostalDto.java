package com.german.codigos_postales.entity;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseCodigoPostalDto {

	private String codigo_postal;
	private String municipio;
	private String ciudad;
	private String estado;
	private List<AsentamientoDto> asentamientos;
	
	public String getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<AsentamientoDto> getAsentamientos() {
		return asentamientos;
	}
	public void setAsentamientos(List<AsentamientoDto> asentamientos) {
		this.asentamientos = asentamientos;
	}
	
	
	
}
