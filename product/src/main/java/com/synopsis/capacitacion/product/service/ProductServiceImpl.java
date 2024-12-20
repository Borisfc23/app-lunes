package com.synopsis.capacitacion.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synopsis.capacitacion.product.entities.Product;
import com.synopsis.capacitacion.product.exception.ResourceNotFound;
import com.synopsis.capacitacion.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Producto no encontrado!!😥"));
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(Product producto) {
		// TODO Auto-generated method stub
		return productRepository.save(producto);
	}

	@Override
	public Product updatedProduct(Product producto) {
		// TODO Auto-generated method stub
		return productRepository.save(producto);
	}

	@Override
	public  void deleteProduct(Long id) {
		productRepository.deleteById(id);	
	}

	@Override
	public boolean existsByCode(String code) {
		// TODO Auto-generated method stub
		return productRepository.existsByCode(code);
	}

}
