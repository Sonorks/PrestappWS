package com.edu.udea.prestapp.ws;

import java.util.Date;

import javax.ws.rs.DELETE;
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

@Path("reserva")
@Component
public class ReservaWS {
	
	@Autowired
	ReservaBL reservaBL;
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("reserva")
	public String reservar(@QueryParam("usuario")String usuario, @QueryParam("idObjeto")int idObjeto, @QueryParam("fechaPrestamo")Date fechaPrestamo){
		try{
			reservaBL.reservarObjeto(usuario, idObjeto, fechaPrestamo);
			return "Reserva realizada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("cancelarReserva/{id}")
	public String cancelar(@PathParam("id")int id, @QueryParam("usuario")String usuario){
		try{
			reservaBL.cancelarReserva(id, usuario);
			return "Reserva cancelada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("modificarReserva")
	public String modificar(@QueryParam("id")int id, @QueryParam("usuario")String usuario, @QueryParam("nuevaFecha")Date nuevaFecha){
		try{
			reservaBL.modificarReserva(id, usuario, nuevaFecha);
			return "Reserva modificada";
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	
}
