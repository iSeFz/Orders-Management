package com.example.ordermanagement.service;

import com.example.ordermanagement.model.SimpleOrder;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderService {
    public String listDetails() { // business logic
        return "Order details";
    }

    public void addProduct() { // business logic
        System.out.println("Product added");
    }

    public double calculateCost() { // business logic
        return 100.0;
    }

    public int getRemainingCount() { // business logic
        return 10;
    }

    public double calculateShipmentFee(SimpleOrder order) {
        int numberOfProducts = order.getProducts().size();
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product

        // Calculate the total fee
        double totalFee = baseFee + (feePerProduct * numberOfProducts);

        return totalFee;
    }

    public boolean shipOrder(SimpleOrder order) {
        // store shipping fee
        double fee = calculateShipmentFee(order);
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
