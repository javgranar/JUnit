package com.example.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.junit.bbdd.BaseDatosServiceI;
import com.example.junit.model.Articulo;

@SpringBootApplication
public class JUnitApplication implements CommandLineRunner{
	
	@Autowired
	private BaseDatosServiceI baseDatosService;

	public static void main(String[] args) {
		SpringApplication.run(JUnitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		baseDatosService.initBD();
		
		Articulo articulo = new Articulo("Galletas", 12.25);
		baseDatosService.insertarArticulo(articulo);
		baseDatosService.findArticuloById(baseDatosService.lastIndex());
		
	}

}
