package com.edu.udea.prestapp.ws;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.PrestamoBL;

@Path("prestamo")
@Component
public class PrestamoWS {
	
	@Autowired
	PrestamoBL prestamoBL;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("realizarPrestamo")
	public String realizarPrestamo(@QueryParam("usuario")String usuario, @QueryParam("id")String id) {
		int idObjeto = Integer.parseInt(id);
		try {
			prestamoBL.realizarPrestamo(usuario, idObjeto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("realizarDevolucion")
	public String realizarDevolucion(@QueryParam("admin")String admin,
			@QueryParam("idObjeto")String idObjeto,
			@QueryParam("idUsuario")String idUsuario
			) {
		int idObj = Integer.parseInt(idObjeto);
		int idUser = Integer.parseInt(idUsuario);
		try {
			prestamoBL.realizarDevolucion(admin, idObj, idUser);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "listo";
	}
	
}
