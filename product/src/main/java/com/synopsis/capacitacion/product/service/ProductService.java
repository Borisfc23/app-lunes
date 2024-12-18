package com.synopsis.capacitacion.product.service;

import java.util.List;

import com.synopsis.capacitacion.product.entities.Product;

public interface ProductService {
	public Product getById(Long id);
	public List<Product> getAll();
	public Product createProduct(Product producto);
	public Product updatedProduct(Product producto);
	public void deleteProduct(Long id);
	public boolean existsByCode(String code);
}

