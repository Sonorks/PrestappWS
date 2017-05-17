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


@Path("objeto")
@Component
public class ObjetoWS {

	@Autowired
	ObjetoBL objetoBL;
	
	@Autowired
	UsuarioBL usuarioBL;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("disponibles")
	public String obtenerObjetosDisponibles() {
		List<Objeto> lista = null;
		String json= null;
		try {
			lista = objetoBL.mostrarObjetos();
			json = new Gson().toJson(lista);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("prestados")
	public String obtenerObjetosPrestados() {
		List<Objeto> lista = null;
		String json= null;
		try {
			lista = objetoBL.mostrarObjetosPrestados();
			json = new Gson().toJson(lista);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("modificarDisponibilidad")
	public String modificarDisponibilidad(@QueryParam("id")String id, @QueryParam("tipoCambio")String tipoCambio) {
		System.out.println("id: "+id+ " "+tipoCambio);
		int idObj = Integer.parseInt(id);
		int tipoCambioObj = Integer.parseInt(tipoCambio);
		try {
			objetoBL.modificarDisponibilidad(idObj, tipoCambioObj);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "listo";
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("eliminar/{id}")
	public String eliminarObjeto(@PathParam("id")String id, @QueryParam("usuario")String usuario) {
		int idObjeto = Integer.parseInt(id);
		System.out.println(idObjeto);
		try {
			objetoBL.eliminarObjeto(usuario, idObjeto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}	
}
