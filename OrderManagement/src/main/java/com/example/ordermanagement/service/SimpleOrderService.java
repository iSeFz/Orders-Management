package com.example.ordermanagement.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleOrderService {
    public String listDetails() { // business logic
        return "Order details";
    }

    public void addProduct() { // business logic
        System.out.println("Product added");
    }

    public double calculateCost() { // business logic
        return 100.0;
    }

    public int getReamaningCount() { // business logic
        return 10;
    }
}
