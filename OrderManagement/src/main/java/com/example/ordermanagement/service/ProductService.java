package com.example.ordermanagement.service;

import com.example.ordermanagement.Common;
import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    private List<Category> categories;
    public Common common = new Common();
    public ProductService(){
        categories = common.categories;
    }
    public String listDetails() { // business logic
        return "Product Details";
    }
    public List<Category> getProducts(){
        return categories;
    }

    public boolean addProduct(Product p){
        for(Category c : categories){
            if(c.getName() == p.getCategoryName()){
                for(Product pr : c.getProducts()){
                    if(pr.getName() == p.getName()){
                        return false;
                    }
                }
                c.addProduct(p);
                return true;
            }
        }
        categories.add(new Category(p.getCategoryName()));
        categories.getLast().addProduct(p);
        return true;
    }
    public int getRemainingCount(Product P) { // business logic
        return P.getRemainingCount();
    }
}
