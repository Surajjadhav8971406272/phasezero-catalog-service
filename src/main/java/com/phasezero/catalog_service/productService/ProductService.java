package com.phasezero.catalog_service.productService;

import java.util.List;

import com.phasezero.catalog_service.model.Product;

public interface ProductService {
	
	Product addProduct(Product product);

    // 2. List all products with pagination + sorting
    List<Product> getAllProducts(int page, int size, String sort, boolean desc);

    // 3. Search by partName
    List<Product> searchByName(String name);

    // 4. Filter by category
    List<Product> filterByCategory(String category);

    // 5. Sort by price (ascending)
    List<Product> sortByPrice();

    // 6. Total inventory value
    double getTotalInventoryValue();

}
