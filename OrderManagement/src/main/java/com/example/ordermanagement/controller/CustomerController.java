package com.example.ordermanagement.controller;

import com.example.ordermanagement.service.CustomerService;
import com.example.ordermanagement.model.Customer;

import java.util.List;

import org.springframework.web.bind.annotation.*;

// Rest Controller for customers
@RestController
@RequestMapping("/customers")
public class CustomerController {
    // Customer Service to be injected
    private final CustomerService customerService;

    // Constructor to inject the service
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create a new account for a customer
    @PostMapping("/createAccount")
    public Customer createAccount(@RequestParam String name, @RequestParam String email,
            @RequestParam String password, @RequestParam double balance) {
        return customerService.addCustomer(name, email, password, balance);
    }

    // Deduct the cost of an order from the customer's balance
    @PutMapping("/deductCost/{cost}")
    public String deductCost(@RequestBody Customer customer, @PathVariable("cost") double cost) {
        return customerService.deductCost(customer, cost);
    }

    /* // Deduct the shipping fees from the customer's balance
    @PutMapping("/deductShippingFees/{fees}")
    public String deductShippingFees(@RequestBody Customer customer, @PathVariable("fees") double fees) {
        return customerService.deductShippingFees(customer, fees);
    } */

    // Get all system customers
    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }
}
