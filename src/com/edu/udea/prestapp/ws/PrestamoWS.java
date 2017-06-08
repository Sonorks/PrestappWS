package com.edu.udea.prestapp.ws;

import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.dto.Objeto;
import com.edu.udea.prestapp.dto.Prestamo;
import com.edu.udea.prestapp.bl.PrestamoBL;
import com.edu.udea.prestapp.exception.ExceptionController;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	final Logger log = Logger.getLogger(PrestamoWS.class.getName());
	@Autowired
	PrestamoBL prestamoBL;
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("realizarPrestamo")//Direccion con la cual se ingresa al servicio
	//Metodo para realizar un prestamo en el laboratorio
	public String realizarPrestamo(
			@QueryParam("usuario")String usuario, 
			@QueryParam("id")String id)//Id del objeto 
		{
		int idObjeto = Integer.parseInt(id);//Se hace el parsing para poder operar los numeros en el metodo
		try {
			System.out.println("Prestamo a realizar");
			prestamoBL.realizarPrestamo(usuario, idObjeto);//Se llama el metodo desde prestamoBL
		}catch(ExceptionController e) {
			log.error("error al realizar Prestamo");
			e.getMessage();
		}
		String json = new Gson().toJson("listo");
		return json;
	}
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("realizarDevolucion")//Direccion con la cual se ingresa al servicio
	//Metodo para realizar una devolucion de un objeto, previamente prestado
	public String realizarDevolucion(@QueryParam("admin")String admin,
			@QueryParam("idObjeto")int idObjeto,
			@QueryParam("idUsuario")int idUsuario
			) {
		String json = null;
		//Se hace el parsing para poder operar los numeros en el metodo
		try {
			System.out.println("paso 1");
			prestamoBL.realizarDevolucion(admin, idObjeto, idUsuario);//Se llama el metodo desde prestamoBL
		}catch(ExceptionController e) {
			log.error("Error al realizar devolucion");
			e.getMessage();
		}
		json = new Gson().toJson("listo");
		return json;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("obtenerPrestamos")
	public String obtenerPrestamos() throws ExceptionController {
		List<Prestamo> lista = null;//Lista en donde se guardan los objetos disponibles
		String json= null;
		lista = prestamoBL.obtenerPrestamos();//Llama el metodo desde prestamoBL
		System.out.println(lista);
		//json = new Gson().toJson(lista);//Manejamos la libreria gson de google, por lo que hay que añadirla en el build path
		json = new Gson().toJson("trabajo futuro");
		return json;
	}
	
}
