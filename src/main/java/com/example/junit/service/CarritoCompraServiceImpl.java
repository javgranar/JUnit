package com.example.junit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.junit.bbdd.BaseDatosServiceI;
import com.example.junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI {
	
	@Autowired
	private BaseDatosServiceI baseDatosService;
	
	List<Articulo> listaArticulos = new ArrayList<>();

	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
		
	}

	@Override
	public void addArticulo(Articulo a) {
		listaArticulos.add(a);
		
	}

	@Override
	public int getNumArticulo() {
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		Double total = 0.0;
		for (Articulo a : listaArticulos) {
			total = total + a.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
		return precio - precio*porcentajeDescuento/100;
	}

	@Override
	public Double aplicarDescuento(Integer id, Double porcentaje) {
		Double resultado = null;
		Articulo a = baseDatosService.findArticuloById(id);
		if (a != null) {
			resultado = calculadorDescuento(a.getPrecio(), porcentaje);
		} else {
			System.out.println("No se ha encontrado el articulo con Id: " + id);
		}
		return resultado;
	}
	
	@Override
	public Integer insertarArticulo(Integer id, Articulo articulo) {
		return baseDatosService.insertarArticuloById(id, articulo);
		
	}

}
