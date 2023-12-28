package com.example.ordermanagement.service;

import com.example.ordermanagement.repos.ProductsRepo;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.Category;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

// Products Service
@Service
public class ProductService {
    // Product repo & category service to be injected
    @Autowired
    private final ProductsRepo productsRepo;
    @Autowired
    private final CategoryService categoryService;

    // Constructor to inject the repo & the category service
    public ProductService(ProductsRepo productsRepo, CategoryService categoryService) {
        this.productsRepo = productsRepo;
        this.categoryService = categoryService;
        // Add existing products to their categories
        for (Product product : productsRepo.getProducts())
            categoryService.addProductToCategory(product);
    }

    // Add a new product to the list of products
    public Product addProduct(int serialNum, String name, String vendor,
            double price, String categoryName, int remainingCount) {
        Product newProduct = new Product(serialNum, name, vendor, price, categoryName, remainingCount);
        // Add the new product to its corresponding category
        categoryService.addProductToCategory(newProduct);
        // Add the new product to the list of products in the products repo
        return productsRepo.addProduct(newProduct);
    }

    // Return all system products
    public List<Product> getProducts() {
        return productsRepo.getProducts();
    }

    // Return all system categories
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    // Return the reamining count of certain product
    public int getRemainingCount(Product product) {
        return product.getRemainingCount();
    }
}
