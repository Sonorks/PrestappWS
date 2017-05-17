package com.edu.udea.prestapp.dto;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReservaJersey {
	//La reserva se aplica a los objetos que se prestan en el laboratorio
	private int id;
	private Usuario usuario;
	private Objeto objeto;
	private Date fechaReserva;
	private Date fechaPrestamo;
	
	public ReservaJersey(Usuario usuario, Objeto objeto, Date fechaReserva, Date fechaPrestamo) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.fechaReserva = fechaReserva;
		this.fechaPrestamo = fechaPrestamo;
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
	
	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
}
