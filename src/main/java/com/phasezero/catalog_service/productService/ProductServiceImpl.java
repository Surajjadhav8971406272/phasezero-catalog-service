package com.phasezero.catalog_service.productService;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.phasezero.catalog_service.exception.DuplicatePartNumberException;
import com.phasezero.catalog_service.exception.ProductNotFoundException;
import com.phasezero.catalog_service.model.Product;
import com.phasezero.catalog_service.productRepository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product payload is required");
        }

   
        if (product.getPartName() == null || product.getPartNumber() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "partName and partNumber are required");
        }

   
        product.setPartName(product.getPartName().toLowerCase().trim());


        String pn = product.getPartNumber().trim();
        product.setPartNumber(pn);

   
        if (productRepository.existsByPartNumber(pn)) {
            throw new DuplicatePartNumberException(
                    "Product with partNumber already exists: " + pn
            );
        }

      
        if (product.getPrice() < 0 || product.getStock() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price and stock cannot be negative");
        }

        return productRepository.save(product);
    }

  
    @Override
    public List<Product> getAllProducts(int page, int size, String sort, boolean desc) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (sort == null || sort.isBlank()) sort = "id";

        PageRequest pageRequest = PageRequest.of(page - 1, size,
                desc ? Sort.by(sort).descending() : Sort.by(sort).ascending());
        return productRepository.findAll(pageRequest).getContent();
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = productRepository.findByPartNameContainingIgnoreCase(name == null ? "" : name);
        if (result.isEmpty()) {
            throw new ProductNotFoundException("No product found with name: " + name);
        }
        return result;
    }

    @Override
    public List<Product> filterByCategory(String category) {
        List<Product> result = productRepository.findByCategoryIgnoreCase(category == null ? "" : category);
        if (result.isEmpty()) {
            throw new ProductNotFoundException("No product found in category: " + category);
        }
        return result;
    }


    @Override
    public List<Product> sortByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }


    @Override
    public double getTotalInventoryValue() {
        return productRepository.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}
