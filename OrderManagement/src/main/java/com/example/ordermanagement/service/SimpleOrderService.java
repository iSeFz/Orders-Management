package com.example.ordermanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.OrderComponent;
import com.example.ordermanagement.model.Product;
import com.example.ordermanagement.model.SimpleOrder;

import com.example.ordermanagement.repos.CustomersRepo;

@Service
public class SimpleOrderService implements OrderComponentService {
    // Customer Repo Instance
    @Autowired
    private final CustomersRepo customersRepo;

    // Parameterized Constructor
    public SimpleOrderService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

    // Get certain order
    @Override
    public OrderComponent getCertainOrder(String customerName, Integer orderID) {
        // Find the customer
        Customer customer = customersRepo.getCustomer(customerName);
        // Find the specified order
        for (OrderComponent order : customer.getOrders()) {
            if (order.getOrderId() == orderID)
                return order;
        }
        return null;
    }

    // List details of specific order
    @Override
    public String getOrderDetails(String customerName, Integer orderID) {
        OrderComponent order = getCertainOrder(customerName, orderID);
        return (order == null) ? "Order NOT found!" : order.listDetails();
    }

    // Calculate total cost of order
    public double calclateTotalCost(String customerName, Integer orderID) {
        // Get the specified order
        SimpleOrder order = (SimpleOrder) getCertainOrder(customerName, orderID);
        // Calculate order total cost
        double orderCost = 0.0;
        for (Product product : order.getProducts())
            orderCost += product.getPrice();
        // Set the order total cost
        order.setTotalCost(orderCost);
        // Return the total cost of the order
        return orderCost;
    }

    // Deduct total cost of order from the customer's balance
    @Override
    public boolean deductTotalCost(String customerName, Integer orderID) {
        // Calculate order total cost
        double orderCost = calclateTotalCost(customerName, orderID);
        // Find the customer
        Customer customer = customersRepo.getCustomer(customerName);
        // Deduct the order cost from the customer's balance
        double newBalance = customer.getBalance() - orderCost;
        // Check if the customer has enough balance
        if (newBalance >= 0) {
            // Set the new balance
            customer.setBalance(newBalance);
            // Update the customer's info in the repo
            customersRepo.updateCustomerBalance(customerName, newBalance);
            return true;
        }
        return false;
    }

    public double calculateShipmentFee(String customerName, Integer orderID) {
        // Get the specified order
        SimpleOrder order = (SimpleOrder) getCertainOrder(customerName, orderID);
        // Get number of products in the order
        int numberOfProducts = order.getProducts().size();
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product
        // Calculate the total shipping fees
        double shippingFees = baseFee + (feePerProduct * numberOfProducts);
        // Set the order shipping fees
        // order.setShippingFees(shippingFees);
        return shippingFees;
    }

    @Override
    public boolean deductShippingFees(String customerName, Integer orderID) {
        // Get the specified order
        SimpleOrder order = (SimpleOrder) getCertainOrder(customerName, orderID);
        // store shipping fee
        double fee = calculateShipmentFee(customerName, orderID);
        order.setShippingFees(fee);
        // check if the balance of the customer is enough
        if (order.getCustomer().getBalance() < order.getShippingFees()) {
            return false;
        }
        // deduct the shipping fee from the customer's balance
        order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getShippingFees());
        return true;
    }
}
