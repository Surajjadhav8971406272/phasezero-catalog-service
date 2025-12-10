package com.phasezero.catalog_service.productRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phasezero.catalog_service.model.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByPartNumber(String partNumber);

	List<Product> findByPartNameContainingIgnoreCase(String name);

	List<Product> findByCategoryIgnoreCase(String category);

	List<Product> findAllByOrderByPriceAsc();




}
