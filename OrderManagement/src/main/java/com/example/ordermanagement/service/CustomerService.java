package com.example.ordermanagement.service;

import com.example.ordermanagement.repos.CustomersRepo;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.OrderComponent;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {
    // Repo to be injected
    @Autowired
    private final CustomersRepo customersRepo;

    // Constructor to inject the repo
    public CustomerService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    // Create a new account for a customer & add it to the list of customers
    public Customer addCustomer(String username, String email, String password, double balance) {
        Customer newCustomer = new Customer(username, email, password, balance);
        return customersRepo.addCustomer(newCustomer);
    }

    // Deduct the cost of an order from the customer's balance
    public String deductCost(Customer customer, double amount) {
        double newBalance = customer.getBalance() - amount;
        // Check if the customer has enough balance
        if (newBalance >= 0) {
            // Set the new balance
            customer.setBalance(newBalance);
            // Update the customer's info in the repo
            customersRepo.updateCustomerBalance(customer);
            return "Cost deducted successfully!";
        } else
            return "Insufficient balance";
    }

    /* // Deduct the shipping fees from the customer's balance
    public String deductShippingFees(Customer customer, double amount) {
        double newBalance = customer.getBalance() - amount;
        // Check if the customer has enough balance
        if (newBalance >= 0) {
            // Set the new balance
            customer.setBalance(newBalance);
            // Update the customer's info in the repo
            customersRepo.updateCustomerBalance(customer);
            return "Shipping fees deducted successfully!";
        } else
            return "Insufficient balance";
    } */

    // Return all system customers
    public List<Customer> getCustomers() {
        return customersRepo.getCustomers();
    }

    public OrderComponent createOrder() { // make busniss logic
        return null;
    }

    public boolean shipOrder(OrderComponent order) { // make busniss logic
        return false;
    }
}
