package com.synopsis.capacitacion.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synopsis.capacitacion.product.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	boolean existsByCode(String code); 
}
