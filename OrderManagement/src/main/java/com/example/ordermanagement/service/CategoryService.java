package com.example.ordermanagement.service;

import com.example.ordermanagement.repos.CategoriesRepo;
import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Product;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// Category Service
@Service
public class CategoryService {
    // Repo to be injected
    @Autowired
    private final CategoriesRepo categoriesRepo;

    // Constructor to inject the repo
    public CategoryService(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    // Add product to certain category
    public String addProductToCategory(Product newProduct) {
        return categoriesRepo.addProductToCategory(newProduct);
    }

    // Return the list of current categories on the system
    public List<Category> getCategories() {
        return categoriesRepo.getCategories();
    }
}
