package com.idat.idatapirest.dto;


public class UserRequestDto {
	
	private String usuario;
	private String contraseña;
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public UserRequestDto(String usuario, String contraseña) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	public UserRequestDto() {
		super();
	}
	
	

}
