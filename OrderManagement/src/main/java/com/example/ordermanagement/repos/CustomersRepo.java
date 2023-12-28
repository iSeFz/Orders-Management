package com.example.ordermanagement.repos;

import com.example.ordermanagement.model.Customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

// Repository for the Customers
@Repository
public class CustomersRepo {
    private List<Customer> customers;

    // Default Constructor
    public CustomersRepo() {
        this.customers = new ArrayList<Customer>();
    }

    // Parameterized Constructor
    public CustomersRepo(List<Customer> customers) {
        this();
        this.customers = customers;
    }

    // Add a new customer to the list of customers
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    // Update current customer's info
    public void updateCustomerBalance(Customer customer) {
        for (Customer tempCustomer : customers) {
            if (tempCustomer.getUsername().equals(customer.getUsername())) {
                tempCustomer.setBalance(customer.getBalance());
            }
        }
    }

    // Return the list of current customers on the system
    public List<Customer> getCustomers() {
        return customers;
    }
}
