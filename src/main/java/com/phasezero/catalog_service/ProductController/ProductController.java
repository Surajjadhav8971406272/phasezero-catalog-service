package com.phasezero.catalog_service.ProductController;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phasezero.catalog_service.model.Product;
import com.phasezero.catalog_service.productService.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product addProduct(@Valid @RequestBody Product product) {
        return productService.addProduct(product);
    }
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String sort,@RequestParam(defaultValue = "false") boolean desc ) {
        return productService.getAllProducts(page,size,sort,desc);
    }
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam("name") String name) {
        return productService.searchByName(name);
    }
    @GetMapping("/filter")
    public List<Product> filterByCategory(@RequestParam String category) {
        return productService.filterByCategory(category);
    }
    @GetMapping("/sort/price")
    public List<Product> sortByPrice() {
        return productService.sortByPrice();
    }
    @GetMapping("/inventory/value")
    public double inventoryValue() {
        return productService.getTotalInventoryValue();
    }
}