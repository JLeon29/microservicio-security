package com.idat.idatapirest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // porque es una clase configuracion
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// En la calse general de SpringSecurity se inyecta las clases que se ha utilizado en el JWT 
	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Autowired
	private JWTUserDetailService jWTUserDetailService;
	
	@Autowired
	private JWTTokenFilter jWTTokenFilter;
	
	@Autowired
	private JWTEntryPoint jWTEntryPoint;
	

	//SOURCE - OVERRIDE IMPLEMENT METHODS ( para implementar los metodos siempre y cuando se extiende de una clase )
	
	//Metodo permite conectarme con usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//CREAMOS LOS USUARIOS EN MEMORIA
		
		/*
		auth.inMemoryAuthentication().withUser("JUAN").password(encoder().encode("juan123")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser("JOSE").password(encoder().encode("jose123")).roles("USER");
		auth.inMemoryAuthentication().withUser("LEON").password(encoder().encode("leon123")).roles("LOCAL");*/
		
		auth.userDetailsService(jWTUserDetailService).passwordEncoder(encoder()); // userDetailsService para conectarse a BD
		
	}

	
	//Metodo permite configurar los usuarios HTTP
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	/*	
	 * SPRING SECURITY SIN JWT
	 * 
	 * http.authorizeRequests()
	
		.antMatchers("/supplier/v1/*").access("hasRole('ADMIN')")
		.antMatchers("/producto/v1/*").access("hasRole('USER')")
		.antMatchers("/supplier/v1/*").access("hasRole('USER')")
		.antMatchers("/item/v1/*").access("hasRole('LOCAL')")
		.antMatchers("/cliente/v1/*").access("hasRole('LOCAL')")
		.antMatchers("/cliente/v1/*").access("hasRole('ADMIN')")
		.antMatchers("/producto/v1/*").access("hasRole('ADMIN')")
		.antMatchers("/item/v1/*").access("hasRole('ADMIN')")
			
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
	
		http.authorizeRequests().antMatchers("/producto/v1/listar").permitAll();	 */
		
		
		
		
		// SPRING SECURITY CON JWT
		
		http.authorizeHttpRequests()
			.antMatchers("/crearToken").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(jWTEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilterBefore(jWTTokenFilter, UsernamePasswordAuthenticationFilter.class)
			.csrf().disable();
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	

}
