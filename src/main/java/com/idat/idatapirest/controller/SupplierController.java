package com.idat.idatapirest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier/v1")
public class SupplierController {
	
	@GetMapping("/mensaje")
	public void mensaje() {
		
		System.out.println("Iniciando la controlador SupplierController ...");
	}

}
