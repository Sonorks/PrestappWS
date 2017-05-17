package com.edu.udea.prestapp.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.UsuarioBL;
import com.edu.udea.prestapp.dto.Usuario;
import com.edu.udea.prestapp.exception.ExceptionController;
@Path("usuario")
@Component
public class UsuarioWS {
	
	@Autowired
	UsuarioBL usuarioBL;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String autenticar (@QueryParam("login")String login, @QueryParam("pws")String pws) {
		String retorno = login;
		try {
			usuarioBL.doLogin(login, pws);
			return login;
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	public String CambiarContraseña (@QueryParam("usuario")String usuario, @QueryParam("correo")String correo, @QueryParam("contrasenaActual")String contrasenaActual, 
			@QueryParam("contrasenaNueva")String contrasenaNueva, @QueryParam("username")Usuario username){
		
		String retorno = usuario;
		try{
			usuarioBL.restablecerContrasena(usuario,correo,contrasenaActual,contrasenaNueva,username);
			return usuario;
		}catch(ExceptionController e) {
			return e.getMessage();
		} 
	}
}
