package com.idat.idatapirest.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*Tenemos que añadir la dependencia del jwt en el pom por que springsecurity no lo tiene

<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>*/


//Implementar la libreria UserDetailsService

/*
 * implement -> interface
 * extends -> clases
 * */
@Service
public class JWTUserDetailService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//En memoria
		//equals --> para comparar objetos
		if("profesor".equals(username)) {
			return new User("profesor", "123", new ArrayList<>());
		}else {
			//De lo contrario devuelve una excepcion de SpringSecurity
			throw new UsernameNotFoundException("El usuario no existe");
		}
	}
	
	

}
