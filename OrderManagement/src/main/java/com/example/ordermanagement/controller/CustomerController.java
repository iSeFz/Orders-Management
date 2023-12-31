package com.example.ordermanagement.controller;

import com.example.ordermanagement.model.SimpleOrder;
import com.example.ordermanagement.repos.CustomersRepo;
import com.example.ordermanagement.service.CompoundOrderService;
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
    private final CompoundOrderService compoundOrderService;

    // Constructor to inject the service
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        compoundOrderService = new CompoundOrderService(new CustomersRepo());
    }

    // Create a new account for a customer
    @PostMapping("/createAccount")
    public Customer createAccount(@RequestParam String name, @RequestParam String email,
            @RequestParam String password, @RequestParam double balance) {
        return customerService.addCustomer(name, email, password, balance);
    }

    // Place an order endpoint
    @PostMapping("/placeSimpleOrder")
    public SimpleOrder placeSimpleOrder(@RequestParam String customerName,
                                        @RequestBody List<Integer> listOfProducts) {
        return customerService.placeSimpleOrder(customerName, listOfProducts);
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

    // Get all system customers
    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomers() {
        return customerService.getCustomers();
    }
    @GetMapping("/ordereDetails")
    public String getDetails(@RequestParam Integer id , @RequestParam String name){
        return compoundOrderService.getOrderDetails(name,id);
    }
}
