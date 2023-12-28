package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.ProductService;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.Category;

import java.util.List;

import org.springframework.web.bind.annotation.*;

// Rest Controller for products
@RestController
@RequestMapping("/products")
public class ProductController {
    // Product Service to be injected
    private final ProductService productService;

    // Constructor to inject the service
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Add a new product to the list of products
    @PostMapping("/addProduct")
    public Product addProduct(@RequestParam int serialNum, @RequestParam String name, @RequestParam String vendor,
            @RequestParam double price, @RequestParam String categoryName, @RequestParam int remainingCount) {
        return productService.addProduct(serialNum, name, vendor, price, categoryName, remainingCount);
    }

    // Get all system products
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    // Get all system categories
    @GetMapping("/getCats")
    public List<Category> getCategories() {
        return productService.getCategories();
    }
}
