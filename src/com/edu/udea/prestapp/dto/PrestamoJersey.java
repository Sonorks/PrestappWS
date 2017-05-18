package com.edu.udea.prestapp.dto;

import java.util.Date;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se detallan los atributos de los Prestamos.
 * 
 * prestamoID es el objeto que compone la clave primaria.
 * la fechaPrestamo es la fecha en que se realiza el prestamo en cuestion
 * fechaDevolucion es la fecha en la que se devuelve el prestamo
 * fechaReserva es donde se almacena la reserva del prestamo si así se necesita.
 */
public class PrestamoJersey {

	private PrestamoID id;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Date fechaReserva;

	public PrestamoID getId() {
		return id;
	}
	public void setId(PrestamoID id) {
		this.id = id;
	}
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
}
