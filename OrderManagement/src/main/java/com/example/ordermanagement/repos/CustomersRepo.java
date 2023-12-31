package com.example.ordermanagement.repos;

import com.example.ordermanagement.model.Customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

// Repository for the Customers
@Repository
public class CustomersRepo {
    private List<Customer> customers;

    // Default Constructor
    public CustomersRepo() {
        this.customers = new ArrayList<Customer>();
        Customer c1 = new Customer("Mostafa", "mostafa@gmail.com", "12345", 1000);
        Customer c2 = new Customer("Oppad", "oppad@gmail.com", "67890", 2000);
        Customer c3 = new Customer("Seif", "seif@gmail.com", "23456", 3000);
        Customer c4 = new Customer("Yousef", "mostafa@gmail.com", "78901", 5000);
        Customer c5 = new Customer("Osama", "osos@gmail.com", "12345", 6000);
        customers.addAll(Arrays.asList(c1, c2, c3, c4, c5));
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
    public void updateCustomerBalance(String customerName, double newBalance) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(customerName)) {
                customer.setBalance(newBalance);
            }
        }
    }

    // Get certain customer by username
    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username))
                return customer;
        }
        return null;
    }

    // Return the list of current customers on the system
    public List<Customer> getCustomers() {
        return customers;
    }
}
