package com.example.junit.service;

import java.util.List;

import com.example.junit.model.Articulo;

public interface CarritoCompraServiceI {
	
	void limpiarCesta();
	
	void addArticulo(Articulo a);
	
	int getNumArticulo();
	
	List<Articulo> getArticulos();
	
	Double totalPrice();
	
	Double calculadorDescuento(double precio, double porcentajeDescuento);
	
	Double aplicarDescuento(Integer id, Double porcentaje);

	Integer insertarArticulo(Integer id, Articulo articulo);


}
