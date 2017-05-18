package com.edu.udea.prestapp.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.PrestamoBL;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se crearan los servicios necesarios para obtener la informacion
 * de los metodos creados en la clase PrestamoBL.
 * 
 * con el método realizarPrestamo se hace una peticion tipo POST para añadir un nuevo
 * prestamo a la base de datos por medio de la url /prestamo/realizarPrestamo
 * recibiendo los parametros de tipo String usuario(nombre de usuario) y el id del objeto
 * 
 * El metodo realizarDevolucion realiza una nueva devolución usando POST 
 * en la url prestamo/realizarDevolucion recibiendo como parametros el usuario del admin,
 * el id del objeto y el id del usuario, todo de tipo String
 */

@Path("prestamo")
@Component
public class PrestamoWS {
	
	@Autowired
	PrestamoBL prestamoBL;
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("realizarPrestamo")//Direccion con la cual se ingresa al servicio
	//Metodo para realizar un prestamo en el laboratorio
	public String realizarPrestamo(@QueryParam("usuario")String usuario, @QueryParam("id")String id) {
		int idObjeto = Integer.parseInt(id);//Se hace el parsing para poder operar los numeros en el metodo
		try {
			prestamoBL.realizarPrestamo(usuario, idObjeto);//Se llama el metodo desde prestamoBL
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("realizarDevolucion")//Direccion con la cual se ingresa al servicio
	//Metodo para realizar una devolucion de un objeto, previamente prestado
	public String realizarDevolucion(@QueryParam("admin")String admin,
			@QueryParam("idObjeto")String idObjeto,
			@QueryParam("idUsuario")String idUsuario
			) {
		int idObj = Integer.parseInt(idObjeto);
		int idUser = Integer.parseInt(idUsuario);
		//Se hace el parsing para poder operar los numeros en el metodo
		try {
			prestamoBL.realizarDevolucion(admin, idObj, idUser);//Se llama el metodo desde prestamoBL
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}
	
}
