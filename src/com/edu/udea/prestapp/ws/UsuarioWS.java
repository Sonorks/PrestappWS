package com.edu.udea.prestapp.ws;

import javax.print.attribute.standard.Media;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edu.udea.prestapp.bl.UsuarioBL;
import com.edu.udea.prestapp.dto.Usuario;
import com.edu.udea.prestapp.exception.ExceptionController;

/**
 * @author Cristian Berrio - cbp453252.hdrl@gmail.com
 * @author Julian Vasquez - julivas96@gmail.com
 * @author David Acevedo - davida.acevedo@udea.edu.co
 * @version = 1.0
 * 
 * En esta clase se crearan los servicios necesarios para obtener la informacion
 * de los metodos creados en la clase UsuarioWS
 * 
 * Se tiene un metodo de tipo POST para realizar el logeo de una manera mas segura
 * solicitando el login del usuario y la contraseña para confirmar la identidad.
 * 
 * con el metodo cambiarContrasena el cual es del tipo PUT se deben obtener el usuario,
 * la nueva contrasena, la contrasena actual y el correo para verificar los datos del usuario
 * antes de realizar los cambios
 * 
 * el metodo registroUsuario que es de tipo POST solicita los datos del usuario para 
 * añadirlo a la base de datos
 * 
 * El metodo modificarDatos permite modificar los datos del usuario por medio de un PUT
 * solicitando los datos generales del usuario y la contraseña para validar la identidad.
 * 
 * Para eliminarUsuario solo es necesario el usuario a eliminar y el usuario del admin
 * para verificar que si se tiene la autorización necesaria.
 * 
 */

@Path("usuario")
@Component
public class UsuarioWS {
	
	@Autowired
	UsuarioBL usuarioBL;
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.TEXT_PLAIN)//Devuelve los datos en texto plano
	@Path("login")//Direccion con la cual se ingresa al servicio
	//Metodo para el login
	public String autenticar (@QueryParam("login")String login, @QueryParam("pws")String pws) {
		String retorno = login;
		try {
			usuarioBL.doLogin(login, pws);//Se implementa el metodo desde el UsuarioBL
			return login;
		}catch(ExceptionController e) {
			return e.getMessage();
		}
	}
	
	@PUT//Para definir que los datos del servicio se envian por PUT
	@Produces(MediaType.TEXT_PLAIN)//Devuelve los datos en texto plano
	@Path("cambiarContrasena")//Direccion con la cual se ingresa al servicio
	public String cambiarContrasena (@QueryParam("usuario")String usuario, @QueryParam("correo")String correo, @QueryParam("contrasenaActual")String contrasenaActual, 
			@QueryParam("contrasenaNueva")String contrasenaNueva, @QueryParam("username")Usuario username){
		
		String retorno = "";
		try{
			usuarioBL.restablecerContrasena(usuario,correo,contrasenaActual,contrasenaNueva,username);//Se implementa el metodo desde el UsuarioBL
			retorno = "Contraseña restablecida";
			return retorno;
		}catch(ExceptionController e) {
			return e.getMessage();
		} 
	}
	
	@POST//Para definir que los datos del servicio se envian por POST
	@Produces(MediaType.TEXT_PLAIN)//Devuelve los datos en texto plano
	@Path("registroUsuario")//Direccion con la cual se ingresa al servicio
	public String registroUsuario(@QueryParam("usuario")String usuario,@QueryParam("contrasena")String contrasena,@QueryParam("contrasena2") String contrasena2,
			@QueryParam("tipoId")String tipoId, @QueryParam("id")int id,@QueryParam("nombres")String nombres,@QueryParam("apellidos")String apellidos,
			@QueryParam("correo")String correo, @QueryParam("telefono")String telefono, @QueryParam("usuarioAdmin")Usuario usuarioAdmin){
		
		String retorno= "";
		try{
			usuarioBL.registrarUsuario(usuario, contrasena, contrasena2, tipoId, id, nombres, apellidos, correo, telefono, usuarioAdmin);
			retorno = "Registro exitoso";
			return retorno;
		}catch(ExceptionController e){
			return e.getMessage();
		}
	}
	
	@PUT//Para definir que los datos del servicio se envian por PUT
	@Produces(MediaType.TEXT_PLAIN)//Devuelve los datos en texto plano
	@Path("modificarDatos")//Direccion con la cual se ingresa al servicio
	public String modificarDatos(@QueryParam("usuario")String usuario, @QueryParam("contrasena")String contraseña, @QueryParam("nombres")String nombres, @QueryParam("apellidos")String apellidos, 
			 @QueryParam("telefono")String telefono, @QueryParam("correo")String correo, @QueryParam("usuarioManipulador")Usuario usuarioManipulador){
		try{
			usuarioBL.modificarDatosDeUsuario(usuario, contraseña, nombres, apellidos, telefono, correo, usuarioManipulador);
			return "Datos modificados exitosamente";
		}catch(ExceptionController e){
			return e.getMessage();
		}
	}
	
	@DELETE//Para definir que los datos del servicio se envian por DELETE
	@Produces(MediaType.TEXT_PLAIN)//Devuelve los datos en texto plano
	@Path("ban/{usuario}")//Direccion con la cual se ingresa al servicio
	public String eliminarUsuario(@QueryParam("usuario")String usuario, @QueryParam("admin")Usuario admin){
		try{
			usuarioBL.eliminarUsuario(usuario, admin);
			return "Baneado papu";
		}catch(ExceptionController e){
			return e.getMessage();
		}
	}
}
