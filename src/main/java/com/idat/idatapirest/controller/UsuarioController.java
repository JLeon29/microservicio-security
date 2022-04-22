package com.idat.idatapirest.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.idat.idatapirest.dto.ClienteResponseDTO;
import com.idat.idatapirest.dto.UserRequestDto;
import com.idat.idatapirest.dto.UserResponseDto;
import com.idat.idatapirest.security.JWTUserDetailService;
import com.idat.idatapirest.security.JwtTokenUtil;

@RestController
public class UsuarioController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JWTUserDetailService jWTUserDetailService;
	
	@RequestMapping(method = RequestMethod.POST, path = "/crearToken")
	public ResponseEntity<?> crearToken(@RequestBody UserRequestDto request){
		
		UserDetails userDetail = jWTUserDetailService.loadUserByUsername(request.getUsuario());
		
		return ResponseEntity.ok(new UserResponseDto(jwtTokenUtil.generateToken(userDetail.getUsername())));
	}

}
