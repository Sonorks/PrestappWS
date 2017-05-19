package com.edu.udea.prestapp.ws;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.PrestamoBL;
import com.edu.udea.prestapp.bl.SancionBL;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se crearan los servicios necesarios para obtener la informacion
 * de los metodos creados en la clase SancionBL.
 * 
 * Se tienen 2 metodos de tipo DELETE y POST para el manejo de las sanciones.
 * 
 * EliminarObjeto es de tipo DELETE como menciona el nombre y recibe un parametro tipo String
 * que es del usuario, y un parametro que se indica en la URL /sancion/eliminar/{id} que es
 * el id de la sanción a eliminar.
 * 
 * sancionarUsuario es de tipo POST y sirve para generar una nueva sancion. recibe los parametros:
 * tipoSancion que se refiere al tipo de sanción a realizar, usuario que es el usuario al que se
 * le realiza la sancion, el admin que se solicita para verificar que la sancion la realice
 * alguien autorizado para esa acción, el idObjeto y el idReserva en caso tal de que se sancione
 * por problemas con la reserva.
 * 
 */

@Path("sancion")
@Component
public class SancionWS {
	final Logger log = Logger.getLogger(SancionWS.class.getName());
	@Autowired
	SancionBL sancionBL;
	
	@DELETE//Para definir que los datos del servicio se envian por DELETE
	@Produces(MediaType.TEXT_PLAIN)//Devuelve la notificacion en texto plano
	@Path("eliminar/{id}")//Direccion con la cual se ingresa al servicio
	//Metodo para eliminar una sancion asignada a un usuario
	public String eliminarSancion(@PathParam("id")String id, @QueryParam("usuario")String usuario) {
		int idSancion = Integer.parseInt(id);//Se hace el parsing para poder operar los numeros en el metodo
		/*System.out.println(idSancion);*/
		try {
			sancionBL.eliminarSancion(idSancion, usuario);//Se llama el metodo desde sancionBL
		}catch(Exception e) {
			log.error("error al eliminar sancion");
			e.printStackTrace();
		}
		return "listo";
	}
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.APPLICATION_JSON)//Devuelve la notificacion en formato JSON
	@Path("sancionar")//Direccion con la cual se ingresa al servicio
	//Metodo para sancionar un usuario
	public String sancionarUsuario(@QueryParam("tipoSancion")String tipoSancion, @QueryParam("usuario")String usuario,
			@QueryParam("admin")String admin, @QueryParam("idObjeto")String idObj, @QueryParam("idReserva")String idRes) {
		int idObjeto = Integer.parseInt(idObj);
		int idReserva = Integer.parseInt(idRes);
		//Se hace el parsing para poder operar los numeros en el metodo
		try {
			sancionBL.sancionarUsuario(tipoSancion, usuario, admin, idObjeto, idReserva);//Se llama el metodo desde sancionBL
		}catch(Exception e) {
			log.error("error al sancionar usuario");
			e.getMessage();
		}
		return "listo";
	}
	
}
