package com.example.junit.bbdd;

import com.example.junit.model.Articulo;

public interface BaseDatosServiceI {
	
	public void initBD();
	
	public Articulo findArticuloById(Integer identificador);
	
	public Integer lastIndex();

	Integer insertarArticuloById(Integer id, Articulo a);

	String insertarArticulo(Articulo articulo);

}
