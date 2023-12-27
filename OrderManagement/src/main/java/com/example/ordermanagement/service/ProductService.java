package com.example.ordermanagement.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public String listDetails() { // business logic
        return "Product Details";
    }

    public int getRemainingCount() { // business logic
        return 0;
    }
}
