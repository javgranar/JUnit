package com.example.junit.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.junit.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI {
	
	Map<Integer, Articulo> storage;

	@Override
	public void initBD() {
		storage = new HashMap<>();
		storage.put(1, new Articulo("Camiseta", 18.95));
		storage.put(2, new Articulo("Pantalon", 10.05));
		storage.put(3, new Articulo("Zapato", 13.99));
		storage.put(4, new Articulo("Pijama", 15.50));
		storage.put(5, new Articulo("Mantita", 28.95));
		storage.put(6, new Articulo("Chaqueta", 17.95));
		storage.put(7, new Articulo("Calcetin", 15.95));
		storage.put(8, new Articulo("Bufanda", 19.95));
		
	}

	@Override
	public Articulo findArticuloById(Integer identificador) {
		System.out.println("Buscando el articulo con Id " + identificador);
		return storage.get(identificador);
	}

	@Override
	public String insertarArticulo(Articulo articulo) {
		System.out.println("Insertando el articulo con nombre: " + articulo.getNombre());
		storage.put(storage.size()+1, articulo);
		return null;
	}
	
	public Integer lastIndex() {
		return storage.size();
	}
	
	@Override
	public Integer insertarArticuloById(Integer id, Articulo articulo) {
		storage.put(id, articulo);
		return id;
	}

}
