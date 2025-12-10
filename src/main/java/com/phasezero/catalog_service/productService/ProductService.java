package com.phasezero.catalog_service.productService;

import java.util.List;

import com.phasezero.catalog_service.model.Product;

public interface ProductService {
	Product addProduct(Product product);
	List<Product> getAllProducts(int page, int size, String sort, boolean desc);
    List<Product> searchByName(String name);
    List<Product> filterByCategory(String category);
    List<Product> sortByPrice();
    double getTotalInventoryValue();

}
