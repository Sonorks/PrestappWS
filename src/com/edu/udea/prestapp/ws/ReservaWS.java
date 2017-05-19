package com.edu.udea.prestapp.ws;

import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.ReservaBL;
import com.edu.udea.prestapp.exception.ExceptionController;
import com.google.gson.Gson;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se crearan los servicios necesarios para obtener la informacion
 * de los metodos creados en la clase ReservaBL.
 * 
 * Con el método reservar se hace una peticion tipo POST para añadir una nueva
 * reserva a la base de datos por medio de la url /reserva/reservar
 * recibiendo los parametros de tipo String usuario(nombre de usuario),el id del objeto
 * y la fecha en la que se va a realizar el prestamo
 * 
 * El metodo cancelarReserva elimina una una reserva por medio de una peticion DELETE,
 * se hace por medio de la id de la reserva en la url /reserva/cancelarReserva/id.
 * Recibe como parametros la id de la reserva y el usuario(username) del usuario
 * que la realizó previamente
 * 
 * El metodo modificarReserva actualiza la fecha de una reserva por medio de una peticion PUT.
 * Recibe como parametros la id de la reserva, el username del usuario y la nueva fecha en la que
 * se realizará el prestamo. Se realiza por medio de la url /reserva/modificarReserva
 */

@Path("reserva")
@Component
public class ReservaWS {
	
	@Autowired
	ReservaBL reservaBL;
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.TEXT_PLAIN)//Devuelve la notificacion en texto plano
	@Path("reservar")//Direccion con la cual se ingresa al servicio
	//Metodo para realizar una reserva de un objeto del laboratorio
	public String reservar(
			@QueryParam("usuario")String usuario, 
			@QueryParam("idObjeto")int idObjeto){
		try{
			Date fechaPrestamo = new Date();
			fechaPrestamo.setTime(fechaPrestamo.getTime()+86400000*8); 
			reservaBL.reservarObjeto(usuario, idObjeto, fechaPrestamo);//Se llama el metodo desde reservaBL
			return "Reserva realizada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	@DELETE//Para definir que los datos del servicio se envian por DELETE
	@Produces(MediaType.TEXT_PLAIN)//Devuelve la notificacion en texto plano
	@Path("cancelarReserva/{id}")//Direccion con la cual se ingresa al servicio
	//Metodo para cancelar una reserva previamente hecha
	public String cancelar(@PathParam("id")int id, @QueryParam("usuario")String usuario){
		try{
			reservaBL.cancelarReserva(id, usuario);//Se llama el metodo desde reservaBL
			return "Reserva cancelada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	@PUT//Para definir que los datos del servicio se envian por PUT
	@Produces(MediaType.TEXT_PLAIN)//Devuelve la notificacion en texto plano
	@Path("modificarReserva")//Direccion con la cual se ingresa al servicio
	//Metodo para modificar la fecha en la que se realiza el prestamo en la reserva
	public String modificar(@QueryParam("id")int id, @QueryParam("usuario")String usuario, @QueryParam("nuevaFecha")Date nuevaFecha){
		try{
			reservaBL.modificarReserva(id, usuario, nuevaFecha);//Se llama el metodo desde reservaBL
			return "Reserva modificada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	
}
