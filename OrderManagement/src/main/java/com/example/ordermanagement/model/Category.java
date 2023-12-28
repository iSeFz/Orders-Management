package com.example.ordermanagement.model;
import java.util.ArrayList;
import java.util.List;
public class Category {
    private String name;
    private List<Product> products;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return this.products;
    }
    public void addProduct(Product p){
        products.add(p);
    }
    public String getName(){
        return name;
    }
}
