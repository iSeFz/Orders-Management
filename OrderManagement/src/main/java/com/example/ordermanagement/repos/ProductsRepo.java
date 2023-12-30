package com.example.ordermanagement.repos;

import com.example.ordermanagement.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

// Repository for the Products
@Repository
public class ProductsRepo {
    private List<Product> products;

    // Default Constructor
    public ProductsRepo() {
        this.products = new ArrayList<Product>();
        Product p1 = new Product(1, "Milk", "Darsh", 40, "Dairy", 10);
        Product p2 = new Product(2, "Cheese", "Oppad", 85, "Dairy", 30);
        Product p3 = new Product(3, "French Fries", "Seif", 25, "Groceries", 90);
        Product p4 = new Product(4, "Onions", "Seif", 40, "Groceries", 30);
        Product p5 = new Product(5, "Kabsa", "Yousef", 1800, "Groceries", 50);
        products.addAll(Arrays.asList(p1, p2, p3, p4, p5));
    }

    // Parameterized Constructor
    public ProductsRepo(List<Product> products) {
        this();
        this.products = products;
    }

    // Add a new product to the list of products
    public Product addProduct(Product newProduct) {
        products.add(newProduct);
        return newProduct;
    }

    // Get certain product by serial number
    public Product getProduct(int serialNum) {
        for (Product product : products) {
            if (product.getSerialNum() == serialNum)
                return product;
        }
        return null;
    }

    // Return the list of current products on the system
    public List<Product> getProducts() {
        return products;
    }
}
