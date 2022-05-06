package com.idat.idatapirest.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.idat.idatapirest.model.Usuario;
import com.idat.idatapirest.repository.UsuarioRepository;

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
	
	@Autowired
	private UsuarioRepository repository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByUsuario(username);
		if(usuario != null) {
			
			//GrantedAuthority permite que se almacenará roles para un usuario
			List<GrantedAuthority> granted = new ArrayList<GrantedAuthority>();
			//Transformamos el rol a tipo GrantedAuthority
			GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol());
			//Agreamos a la lista
			granted.add(authority);
			
			return new User(usuario.getUsuario(), new BCryptPasswordEncoder().encode(usuario.getContrasenia()), granted);
			
		}else {
			throw new UsernameNotFoundException("El usuario no existe");
		}
		
		
		/*
		
		//En memoria
		//equals --> para comparar objetos
		if("profesor".equals(username)) {
			return new User("profesor", "123", new ArrayList<>());
		}else {
			//De lo contrario devuelve una excepcion de SpringSecurity
			throw new UsernameNotFoundException("El usuario no existe");
		}
		
		
		*/
	}
	
	
	
	
	

}
