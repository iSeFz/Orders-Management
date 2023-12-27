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
    private List<Product> productList;
    public Common common = new Common();
    Category c = new Category("maez");
    public ProductService(){
        productList = common.productList;
    }
    public String listDetails() { // business logic
        return "Product Details";
    }
    public List<Product> getProducts(){
        return productList;
    }

    public boolean addProduct(Product p){
        for(Product pr :productList){
            if(Objects.equals(pr.getName(), p.getName())){
                return false;
            }
        }
        productList.add(p);
        return true;
    }
    public int getRemainingCount(Product P) { // business logic
        return P.getRemainingCount();
    }
}
