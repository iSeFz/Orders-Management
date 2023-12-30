package com.example.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.OrderComponent;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.SimpleOrder;
import com.example.ordermanagement.repos.CustomersRepo;
import com.example.ordermanagement.repos.ProductsRepo;

@Service
public class CustomerService {
    // Customer & Product repos to be injected
    @Autowired
    private final CustomersRepo customersRepo;
    @Autowired
    private final ProductsRepo productsRepo;
    // Order Service Instance
    @Autowired
    private final OrderComponentService orderService;

    // Constructor to inject the repo
    public CustomerService(CustomersRepo customersRepo, ProductsRepo productsRepo, OrderComponentService orderService) {
        this.customersRepo = customersRepo;
        this.productsRepo = productsRepo;
        this.orderService = orderService;
    }

    // Create a new account for a customer & add it to the list of customers
    public Customer addCustomer(String username, String email, String password, double balance) {
        Customer newCustomer = new Customer(username, email, password, balance);
        return customersRepo.addCustomer(newCustomer);
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

    // Place a simple order & assign it to its customer with the list of products given
    public String placeSimpleOrder(String customerName, List<Integer> listOfProductSerials) {
        // Get the customer from the repo
        Customer customer = customersRepo.getCustomer(customerName);
        // If the customer doesn't exist, return null
        if (customer == null)
            return "Customer NOT found!";
        // List of products to get from the repo
        List<Product> products = new ArrayList<Product>();
        // Get the products from the repo
        for (Integer serialNum : listOfProductSerials) {
            Product product = productsRepo.getProduct(serialNum);
            // Add the product to the list of customer order products
            if (product != null) {
                // If the product is available, add it to the list
                products.add(product);
                // Decrement the remaining count of the product
                product.setRemainingCount(product.getRemainingCount() - 1);
            }
        }
        // Create a new order
        SimpleOrder newOrder = new SimpleOrder(customer, "Egypt");
        // Add the products to the order
        for (Product product : products)
            newOrder.addProduct(product);
        // Set the order id
        newOrder.setOrderId(generateOrderID());
        // Add the order to the list of customer orders
        customer.addOrder(newOrder);
        // Deduct the order cost from the customer's balance, return the newly created order
        if (orderService.deductTotalCost(customerName, newOrder.getOrderId()))
            return newOrder.listDetails();
        // Otherwise, return null
        return "Insufficient customer balance";
    }
    
    // Return all system customers
    public List<Customer> getCustomers() {
        return customersRepo.getCustomers();
    }

    // Generate random order ID
    public int generateOrderID() {
        return new Random().nextInt(1001);
    }

    public boolean shipOrder(OrderComponent order) { // make busniss logic
        return false;
    }
}
