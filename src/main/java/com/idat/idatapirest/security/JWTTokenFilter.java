package com.idat.idatapirest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;




/*Se inyecta la clase Servicio ya que el TokenFilter se comunica con la clase servicio (según la arquitectura), si se llegara a personaliza el AuthManager
 se podria inyectar el AuthManager pero mas directo es con el servicio ya que este ultimo solo es un bypass ( comunicador)
*/


@Component  //para que spring lo considere en la ejecución como un componente
public class JWTTokenFilter extends OncePerRequestFilter { // Se extiende  OncePerRequestFilter para poder usar el TokenFilter
	
	@Autowired
	private JwtTokenUtil util;
	
	@Autowired
	private JWTUserDetailService jWTUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Obtener el valor
		final String tokenHeader= request.getHeader("Authorization"); //a traves del header authorization (postman) obtenemos el token
		
		String username = null;
		String jwtToken = null;
		
		//Si se cumple
		if(tokenHeader != null && tokenHeader.startsWith("Bearer")) { // startsWith -- valida si el texto tiene la palabra "Bearer" (ejm) como cabecera de texto
			
			//Se guarda
			jwtToken = tokenHeader.substring(7); // con el substring retirar la palabra Bearer 
			
			//Obteniendo el usuario ingresado mediante el token
			try {
				username = util.getUsernameFromToken(jwtToken);
				
				// Y si ocurre un error o el token no tiene el formato 	
			} catch (Exception e) {
				System.out.println("Ocurrio un error");
			}

		} else {
			
			logger.warn("JWT no esta iniciando con Berear");
		}
		
		//Validamos si el usuario se encuentra en la base de datos
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetail = this.jWTUserDetailService.loadUserByUsername(username);
			
			if(util.validateToken(jwtToken, userDetail)) {
				
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(jwtToken, null, userDetail.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	
	

}
