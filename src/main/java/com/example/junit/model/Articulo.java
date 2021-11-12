package com.example.junit.model;

public class Articulo {
	
	private String nombre;
	
	private Double precio;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Articulo(String nombre, Double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}
	
}
