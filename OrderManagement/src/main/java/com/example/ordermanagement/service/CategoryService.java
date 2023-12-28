package com.example.ordermanagement.service;

import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private List<Category> categories;
    public CategoryService(){
        categories = new ArrayList<>();
    }
    public int getRemainingCount() { // business logic
        return 0;
    }

    public void addProductToCategory(Product p , String categoryName) {
        for(Category c:categories){
            if(c.getName() == categoryName){
                c.addProduct(p);
            }
        }
        // business logic
    }

    public void removeProduct(Product p) {
        categories.remove(p);// business logic
    }
}
