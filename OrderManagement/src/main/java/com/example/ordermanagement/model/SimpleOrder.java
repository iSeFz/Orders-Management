package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

// Simple Order class
public class SimpleOrder extends OrderComponent {
    // List of order products
    private List<Product> products;

    // Default Constructor
    public SimpleOrder() {
        super();
        this.products = new ArrayList<Product>();
    }

    // Parametrized Constructor
    public SimpleOrder(Customer customer, String location) {
        this();
        this.setCustomer(customer);
        this.setLocation(location);
    }

    // Add a single product to the list of products
    public Product addProduct(Product product) {
        this.products.add(product);
        return product;
    }

    // Return all products of the order
    public List<Product> getProducts() {
        return this.products;
    }

    // List order details
    @Override
    public String listDetails() {
        return "Simple Order";
    }
}
