package com.example.ordermanagement.model;
import java.util.ArrayList;
import java.util.List;
public class Category {
    private String name;
    private List<Product> products;
    private int remainingItemsForCategory;

    public Category(String name) {
        this.name = name;
        this.products = new ArrayList<>();
        remainingItemsForCategory = 0;
    }

    public List<Product> getProducts() {
        return this.products;
    }
    public void addProduct(Product p){
        products.add(p);
        remainingItemsForCategory+=p.getRemainingCount();
    }
    public String getName(){
        return name;
    }
    public int getRemainingItemsForCategory(){return remainingItemsForCategory;}
}
