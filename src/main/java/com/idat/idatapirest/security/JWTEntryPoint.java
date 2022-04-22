package com.idat.idatapirest.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


//Se implementa los metodos de AuthenticationEntryPoint // se activa cuando pasa algo (ALERTA) Ejm: el usuario no se encuentra en la BD

//Se personaliza el mensaje de error, por eso se utiliza el response

@Component // Es una clase que tambien va a participar en el flujo de spring
public class JWTEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		//se personaliza el mensaje, por eso se utiliza el response
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Autorizado"); // no esta autorizado - cod 401
	
		
	}

}
