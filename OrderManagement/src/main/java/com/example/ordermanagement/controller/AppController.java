package com.example.ordermanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.service.CompoundOrderService;
import com.example.ordermanagement.service.CustomerService;
import com.example.ordermanagement.service.ProductService;
import com.example.ordermanagement.service.SimpleOrderService;

@RestController
@RequestMapping("/api")
public class AppController {
    // Customer Service to be injected
    private final CustomerService customerService;
    // Product Service to be injected
    private final ProductService productService;
    // Simple Order Service to be injected
    private final SimpleOrderService simpleOrderService;
    // Compound Order Service to be injected
    private final CompoundOrderService compoundOrderService;

    // Constructor to inject the service
    public AppController(CustomerService customerService, ProductService productService,
            SimpleOrderService simpleOrderService, CompoundOrderService compoundOrderService) {
        this.customerService = customerService;
        this.productService = productService;
        this.simpleOrderService = simpleOrderService;
        this.compoundOrderService = compoundOrderService;
    }

    // Get all system products
    @GetMapping("/listAllProducts")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    // Create a new account for a customer
    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String name, @RequestParam String email,
            @RequestParam String password, @RequestParam double balance) {
        return customerService.addCustomer(name, email, password, balance);
    }

    // Place a simple order endpoint
    @PostMapping("/placeSimpleOrder")
    public String placeSimpleOrder(@RequestParam String customerName, @RequestBody List<Integer> listOfProducts) {
        return customerService.placeSimpleOrder(customerName, listOfProducts);
    }

    // List order details using its order id endpoint
    @GetMapping("/listSimpleOrderDetails")
    public String listSimpleOrderDetails(@RequestParam String customerName, @RequestParam Integer orderID) {
        return simpleOrderService.getOrderDetails(customerName, orderID);
    }

    // List order details using its order id endpoint
    @GetMapping("/listCompoundOrderDetails")
    public String listCompoundOrderDetails(@RequestParam String customerName, @RequestParam Integer orderID) {
        return compoundOrderService.getOrderDetails(customerName, orderID);
    }

    // Place a compound order endpoint
    @PostMapping("/placeCompoundOrder")
    public String placeCompoundOrder(@RequestParam String customerName, @RequestParam String listOfProducts,
            @RequestBody Map<String, Integer> listOfFriendOrders) {
        List<Integer> listOfProductsInt = new ArrayList<Integer>();
        String[] products = listOfProducts.split(",");
        for (String product : products) {
            listOfProductsInt.add(Integer.parseInt(product));
        }
        return customerService.placeCompoundOrder(customerName, listOfProductsInt, listOfFriendOrders);
    }

    // ship an simple order endpoint
    @PostMapping("/shipSimpleOrder")
    public String shipSimpleOrder(@RequestParam String customerName, @RequestParam Integer orderID) {
        return customerService.shipSimpleOrder(customerName, orderID);
    }

    // ship a compound order endpoint
    @PostMapping("/shipCompoundOrder")
    public String shipCompoundOrder(@RequestParam String customerName, @RequestParam Integer orderID) {
        return customerService.shipCompoundOrder(customerName, orderID);
    }

    // Cancel a simple order endpoint
    @DeleteMapping("/cancelSimpleOrder")
    public String cancelSimpleOrder(@RequestParam String customerName, @RequestParam Integer orderID) {
        return customerService.cancelSimpleOrder(customerName, orderID);
    }

    // Cancel a compound order endpoint
    @DeleteMapping("/cancelCompoundOrder")
    public String cancleCompoundOrder(@RequestParam String customerName, @RequestParam Integer orderID) {
        return customerService.cancelCompoundOrder(customerName, orderID);
    }

    // List All System Orders
    @GetMapping("/listAllOrders")
    public String getAllOrders() {
        return customerService.listAllOrders();
    }

    // For Testing ONLY - List all system customers
    @GetMapping("/listAllCustomers")
    public String getAllCustomer() {
        String allCustomers = "";
        for (Customer customer : customerService.getCustomers())
            allCustomers += customer.toString() + "\n";
        return allCustomers;
    }
}
