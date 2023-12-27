package com.example.ordermanagement.model;
import java.util.ArrayList;
import java.util.List;
public class Category {
    private List<Product> products;

    public Category() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return this.products;
    }
}
