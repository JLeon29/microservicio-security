package com.idat.idatapirest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idat.idatapirest.IdatRestApiApplication;

@RestController
@RequestMapping("/item/v1")
public class ItemController {
	
	@GetMapping("/mensaje")
	public void mensaje() {
		
		System.out.println("Iniciando el controlador ItemController ...");
	}

}
