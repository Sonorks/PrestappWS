package com.edu.udea.prestapp.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.ObjetoBL;
import com.edu.udea.prestapp.bl.UsuarioBL;
import com.edu.udea.prestapp.dto.Objeto;
import com.edu.udea.prestapp.dto.Usuario;
import com.edu.udea.prestapp.exception.ExceptionController;
import com.google.gson.Gson;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se crearan los servicios necesarios para obtener la informacion
 * de los metodos creados en la clase ObjetoBL.
 * 
 * El metodo obtenerObjetosDisponibles es de tipo GET y produce como retorno
 * un objeto tipo JSON con todos los objetos disponibles en la BD
 * 
 * El metodo obtenerObjetosPrestados es similar a obtenerObjetosDisponibles pero 
 * devuelve los objetos no disponibles en la BD
 * 
 * El metodo modificarDisponibilidad es de tipo PUT y sirve para hacer una
 * actualización del estado de los objetos, se usa cuando se realizen prestamos, devoluciones
 * o reservas.
 * 
 * El metodo eliminarObjeto como su nombre lo dice es del tipo DELETE y sirve para
 * eliminar los objetos en caso de que sea necesario.
 * 
 */

@Path("objeto")
@Component
//Objeto, se refiere a los dispositivos que el laboratorio tiene para prestar
public class ObjetoWS {

	@Autowired
	ObjetoBL objetoBL;
	
	@Autowired
	UsuarioBL usuarioBL;
	
	@GET//Para definir que los datos del servicio se envian por GET
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("disponibles")//Direccion con la cual se ingresa al servicio
	//Metodo para obtener una lista con los objetos disponibles en el momento
	public String obtenerObjetosDisponibles() {
		List<Objeto> lista = null;//Lista en donde se guardan los objetos disponibles
		String json= null;
		try {
			lista = objetoBL.mostrarObjetos();//Llama el metodo desde objetoBL
			json = new Gson().toJson(lista);//Manejamos la libreria gson de google, por lo que hay que añadirla en el build path
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@GET//Para definir que los datos del servicio se envian por GET
	@Produces(MediaType.APPLICATION_JSON)//Retorna la lista en formato JSON
	@Path("prestados")//Direccion con la cual se ingresa al servicio
	//Metodo para obtener los objetos prestados en el momento
	public String obtenerObjetosPrestados() {
		List<Objeto> lista = null;//Lista en la cual se guardan los objetos prestados
		String json= null;
		try {
			lista = objetoBL.mostrarObjetosPrestados();//Llama el metodo desde objetoBL
			json = new Gson().toJson(lista);//Manejamos la libreria gson de google, por lo que hay que añadirla en el build path
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PUT//Para definir que los datos del servicio se envian por PUT
	@Consumes(MediaType.TEXT_PLAIN)//Retornara la notificación en texto plano
	@Path("modificarDisponibilidad")//Dirección con la cual se ingresa al servicio
	//Metodo para modificar o actualizar la disponibilidad de un objeto
	public String modificarDisponibilidad(@QueryParam("id")String id, @QueryParam("tipoCambio")String tipoCambio) {
		/*System.out.println("id: "+id+ " "+tipoCambio);*/
		int idObj = Integer.parseInt(id);
		int tipoCambioObj = Integer.parseInt(tipoCambio);
		//Se necesita hacer el parsing para poder operar los numeros en el metodo
		try {
			objetoBL.modificarDisponibilidad(idObj, tipoCambioObj);//Llama el metodo desde objetoBL
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "listo";
	}
	
	@DELETE//Para definir que los datos del servicio se envian por DELETE
	@Produces(MediaType.TEXT_PLAIN)//Retornará la notificación en texto plano
	@Path("eliminar/{id}")//Dirección con la cual se ingresa al servicio. El campo id, es el id del objeto
	//Metodo para eliminar un objeto de la lista de objetos del laboratorio
	public String eliminarObjeto(@PathParam("id")String id, @QueryParam("usuario")String usuario) {
		int idObjeto = Integer.parseInt(id);//Se hace el parsing para poder operar los numeros en el metodo
		/*System.out.println(idObjeto);*/
		try {
			objetoBL.eliminarObjeto(usuario, idObjeto);//Se llama el metodo desde objetoBL
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}	
}
