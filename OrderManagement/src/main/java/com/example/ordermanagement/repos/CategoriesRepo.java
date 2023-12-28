package com.example.ordermanagement.repos;

import com.example.ordermanagement.model.Category;
import com.example.ordermanagement.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

// Repository for the Categories
@Repository
public class CategoriesRepo {
    private List<Category> categories;

    // Default Constructor
    public CategoriesRepo() {
        this.categories = new ArrayList<Category>();
        Category c1 = new Category("Dairy");
        Category c2 = new Category("Groceries");
        Category c3 = new Category("Meats");
        Category c4 = new Category("Fruits");
        Category c5 = new Category("Vegetables");
        categories.addAll(Arrays.asList(c1, c2, c3, c4, c5));
    }

    // Parameterized Constructor
    public CategoriesRepo(List<Category> categories) {
        this();
        this.categories = categories;
    }

    // Add product to certain category
    public String addProductToCategory(Product product) {
        // Search for existing category to add the product into
        for (Category cat : categories) {
            if (cat.getName().equals(product.getCategoryName())) {
                if (cat.getProducts().contains(product))
                    return "Product already exists in its category!";
                else {
                    cat.addProduct(product);
                    return "Product added to " + product.getCategoryName() + " category successfully!\n";
                }
            }
        }
        // If the category is NOT found, create a new one with the specified name
        Category newCategory = new Category(product.getCategoryName());
        // Add the product to the newly created category
        newCategory.addProduct(product);
        // Add it to the list of categories
        categories.add(newCategory);
        return "Product added to " + product.getCategoryName() + " category successfully!\n";
    }

    // Return the list of current categories on the system
    public List<Category> getCategories() {
        return categories;
    }
}
