package com.example.ordermanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ordermanagement.model.*;
import com.example.ordermanagement.repos.CustomersRepo;

@Service
public class CompoundOrderService implements OrderComponentService {
    @Autowired
    private final CustomersRepo customersRepo;

    // Parameterized Constructor
    public CompoundOrderService(CustomersRepo customersRepo) {
        this.customersRepo = customersRepo;
    }

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

    @Override
    public String getOrderDetails(String customerName, Integer orderID) {
        OrderComponent order = getCertainOrder(customerName, orderID);
        return (order == null) ? "Order NOT found!" : order.listDetails();
    }

    public double calculateTotalCost(String customerName, Integer orderID) {
        // Get the specified order
        CompoundOrder order = (CompoundOrder) getCertainOrder(customerName, orderID);
        double totalCost = 0.0;
        for (OrderComponent otherOrder : order.getOtherOrders()) {
            if (otherOrder instanceof SimpleOrder)
                totalCost += ((SimpleOrder) otherOrder).getTotalCost();
        }
        return totalCost;
    }

    // Deduct total cost of order from the customer's balance
    @Override
    public boolean deductTotalCost(String customerName, Integer orderID) {
        // Calculate order total cost
        double orderCost = calculateTotalCost(customerName, orderID);
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
        CompoundOrder order = (CompoundOrder) getCertainOrder(customerName, orderID);
        List<OrderComponent> otherOrders = order.getOtherOrders();
        int numberOfProducts = 0;
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder)
                numberOfProducts += ((SimpleOrder) otherOrder).getProducts().size();
        }
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product

        // Calculate the total fee
        double totalFee = baseFee + (feePerProduct * numberOfProducts);

        return totalFee;
    }

    @Override
    public boolean deductShippingFees(String customerName, Integer orderID) {
        // Get the specified order
        CompoundOrder order = (CompoundOrder) getCertainOrder(customerName, orderID);
        // store shipping fee
        double fee = calculateShipmentFee(customerName, orderID);
        order.setShippingFees(fee);
        List<Customer> customers = new ArrayList<>();
        List<OrderComponent> otherOrders = order.getOtherOrders();

        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder)
                customers.add(((SimpleOrder) otherOrder).getCustomer());
        }

        // check if the balance of the customer is enough
        boolean noBalance = false;
        for (Customer customer : customers) {
            if (customer.getBalance() < (order.getShippingFees() / customers.size())) {
                noBalance = true;
            }
        }

        if (noBalance)
            return false;

        // deduct the shipping fee from the customer's balance
        for (Customer customer : customers) {
            customer.setBalance(customer.getBalance() - (order.getShippingFees() / customers.size()));
        }

        return true;
    }
}
