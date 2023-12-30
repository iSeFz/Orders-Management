package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.SimpleOrderService;

import org.springframework.web.bind.annotation.*;

// Rest Controller for simple orders
@RestController
public class SimpleOrderController {
    // Simple Order Service to be injected
    private final SimpleOrderService simpleOrderService;

    // Constructor to inject the service
    public SimpleOrderController(SimpleOrderService simpleOrderService) {
        this.simpleOrderService = simpleOrderService;
    }

    // List order details using its order id endpoint
    @GetMapping("/orderDetails")
    public String listDetails(@RequestParam String customerName, @RequestParam Integer orderID) {
        return simpleOrderService.getOrderDetails(customerName, orderID);
    }
}
