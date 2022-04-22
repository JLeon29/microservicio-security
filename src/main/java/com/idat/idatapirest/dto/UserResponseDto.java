package com.idat.idatapirest.dto;



public class UserResponseDto {
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserResponseDto(String token) {
		super();
		this.token = token;
	}

	public UserResponseDto() {
		super();
	}
	
	

}
