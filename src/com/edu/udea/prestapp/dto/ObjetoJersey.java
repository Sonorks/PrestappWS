package com.edu.udea.prestapp.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * Esta clase tipo pojo contiene los datos de los Objetos.
 * El id se refiere al identificador unico de cada objeto
 * El nombre corresponde al nombre completo del objeto
 * Disponibilidad determina si un objeto se encuentra en stock(true) o prestado(false)
 * Reservado se refiere a si un objeto se encuentra reservado
 * Estado es el indicador de que el objeto se puede prestar con estados:"funcional", "malo"
 */

@XmlRootElement
public class ObjetoJersey {
	//Dispositivo que se presta en el laboratorio
	private int id;
	private String nombre;
	private boolean disponibilidad;
	private boolean reservado;
	private String estado;
	
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
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public boolean isReservado() {
		return reservado;
	}
	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
