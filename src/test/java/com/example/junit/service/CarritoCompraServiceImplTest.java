package com.example.junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.junit.bbdd.BaseDatosServiceImpl;
import com.example.junit.model.Articulo;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	@InjectMocks
	CarritoCompraServiceImpl carritoCompraService = new CarritoCompraServiceImpl();
	
	@Mock
	BaseDatosServiceImpl baseDatosService = new BaseDatosServiceImpl();
	
	Articulo articulo1 = new Articulo("Hola1", 10.0);
	Articulo articulo2 = new Articulo("Hola2", 10.0);
	
	@BeforeEach
	protected void setUp(){
		carritoCompraService.limpiarCesta();
	}
	
	@Test
	@Order(1)
	void testAddArticulo() {
		carritoCompraService.addArticulo(articulo1);
		assertTrue(carritoCompraService.getArticulos().contains(articulo1));
	}

	@Test
	@Order(2)
	void testTotalPrice() {
		carritoCompraService.addArticulo(articulo1);
		carritoCompraService.addArticulo(articulo2);
		assertTrue(carritoCompraService.totalPrice() == 20.0);
	}

	@Test
	@Order(3)
	void testCalculadorDescuento() {
		assertTrue(carritoCompraService.calculadorDescuento(10, 10) == 9);
	}
	
	@Test
	@Order(4)
	void testLimpiarCesta() {
		carritoCompraService.addArticulo(articulo1);
		
		carritoCompraService.limpiarCesta();
		assertTrue(carritoCompraService.getArticulos().size() == 0);
	}
	
	@Test
	@Order(5)
	void testGetArticulos() {
		carritoCompraService.addArticulo(articulo1);
		carritoCompraService.addArticulo(articulo2);
		
		List<Articulo> lista = carritoCompraService.getArticulos();
		assertTrue(lista.size() == 2);
		assertEquals(lista.get(0).getNombre(), "Hola1");
	}
	
	@Test
	@Order(6)
	void testGetNumArticulo() {
		carritoCompraService.addArticulo(articulo1);
		carritoCompraService.addArticulo(articulo2);
		
		assertTrue(carritoCompraService.getNumArticulo() == 2);
	}
	
	@Test
	@Order(7)
	void testAplicarDescuento() {
		Mockito.when(baseDatosService.findArticuloById(any(Integer.class))).thenReturn(articulo1);
		Double res = carritoCompraService.aplicarDescuento(3, 12.0);
		assertTrue(res == 8.8);
		Mockito.verify(baseDatosService, times(1)).findArticuloById(any(Integer.class));
	}
	
	@Test
	@Order(8)
	void testAplicarDescuentoElse() {
		Mockito.when(baseDatosService.findArticuloById(any(Integer.class))).thenReturn(null);
		Double res = carritoCompraService.aplicarDescuento(3, 12.0);
		assertTrue(res == null);
		Mockito.verify(baseDatosService, times(1)).findArticuloById(any(Integer.class));
	}
	
	@Test
	@Order(9)
	void testInsertarArticulo() {
		Mockito.when(baseDatosService.insertarArticuloById(any(Integer.class), any(Articulo.class))).thenReturn(1);
		Integer id = carritoCompraService.insertarArticulo(1, articulo1);
		assertEquals(1, id);
		carritoCompraService.addArticulo(articulo1);
		assertTrue(carritoCompraService.getArticulos().contains(articulo1));
		verify(baseDatosService, atLeast(1)).insertarArticuloById(any(Integer.class), any(Articulo.class));
		
	}

}
