package com.edu.udea.prestapp.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.UsuarioBL;
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
		/*if(usuarioBL != null) {
			return retorno;
		}
		else {
			return "usuarioBL es nulo";
		}*/
		try {
			usuarioBL.doLogin(login, pws);
			return login;
		}catch(ExceptionController e) {
			return e.getMessage();
		}
		
		
		
	}
}
