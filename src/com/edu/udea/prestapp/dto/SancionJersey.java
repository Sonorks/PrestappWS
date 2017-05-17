package com.edu.udea.prestapp.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class SancionJersey {
	//La sancion se aplica a los usuarios que han prestado objetos, hay varios tipos de sanciones
	private int id;
	private Usuario usuario;
	private String tipoSancion;
	private Date inicioSancion;
	private Date finSancion;
	
	public SancionJersey(){};
	
	public SancionJersey(int id, Usuario usuario, String tipoSancion, Date inicioSancion, Date finSancion) {
		this.id = id;
		this.usuario = usuario;
		this.tipoSancion = tipoSancion;
		this.inicioSancion = inicioSancion;
		this.finSancion = finSancion;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTipoSancion() {
		return tipoSancion;
	}
	public void setTipoSancion(String tipoSancion) {
		this.tipoSancion = tipoSancion;
	}
	public Date getInicioSancion() {
		return inicioSancion;
	}
	public void setInicioSancion(Date inicioSancion) {
		this.inicioSancion = inicioSancion;
	}
	public Date getFinSancion() {
		return finSancion;
	}
	public void setFinSancion(Date finSancion) {
		this.finSancion = finSancion;
	}
}
