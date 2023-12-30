package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Product> products;
    private int remainingItems;

    // Default constructor
    public Category() {
        this.name = "";
        this.products = new ArrayList<Product>();
        this.remainingItems = this.products.size();
    }

    // Parameterized constructor
    public Category(String name) {
        this();
        this.name = name;
    }

    // Add a new product to the category
    public void addProduct(Product product) {
        this.products.add(product);
        this.remainingItems += product.getRemainingCount();
    }

    // Remove a product from the category
    public void removeProduct(Product product) {
        this.products.remove(product);
        this.remainingItems -= product.getRemainingCount();
    }

    // Return the name of the category
    public String getName() {
        return this.name;
    }

    // Return all products in the category
    public List<Product> getProducts() {
        return this.products;
    }

    // Return the remaining amount of all products in the category
    public int getRemainingItems() {
        return this.remainingItems;
    }
}
