package com.synopsis.capacitacion.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synopsis.capacitacion.product.entities.Product;
import com.synopsis.capacitacion.product.exception.ResourceNotFound;
import com.synopsis.capacitacion.product.service.ProductServiceImpl;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl service;
	
	
	@GetMapping("/all")
	public List<Product> listar(){
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductId(@PathVariable Long id) {
		try {
			Product producto = service.getById(id);
			return new ResponseEntity<Product>(producto,HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
	        response.put("error", e.getMessage());	        
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	@PostMapping("/create")
	public ResponseEntity<?> guardarProducot(@Valid @RequestBody Product producto) {
		try {
			if(service.existsByCode(producto.getCode())) {
				throw new ResourceNotFound("El código del producto ya existe");
			}
			return new ResponseEntity<Product>(service.createProduct(producto),HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
	        response.put("error", e.getMessage());	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @Valid @RequestBody Product producto) {
		try {
			Product productoExistente=service.getById(id);
			productoExistente.setCode(producto.getCode());	
			productoExistente.setName(producto.getName());		
			service.updatedProduct(productoExistente);
			return new ResponseEntity<Product>(productoExistente,HttpStatus.OK);
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
	        response.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable Long id) {		
		try {
			Product producto = service.getById(id);
			service.deleteProduct(producto.getId());
			return ResponseEntity.ok().body("Producto eliminado correctamente");
		} catch (Exception e) {
			Map<String, String> response = new HashMap<>();
	        response.put("error", e.getMessage());	        
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
				
	}
		
}
