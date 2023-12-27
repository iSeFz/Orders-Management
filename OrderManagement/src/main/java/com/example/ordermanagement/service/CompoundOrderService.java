package com.example.ordermanagement.service;

import com.example.ordermanagement.model.CompoundOrder;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.OrderComponent;
import com.example.ordermanagement.model.SimpleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@Service
public class CompoundOrderService {
    public String listDetails() { // business logic
        return "Order details";
    }

    public void addOrder() { // business logic // named in class diagram: addChild()
        System.out.println("Order added");
    }

    public void removeOrder() { // business logic // named in class diagram: removeChild()
        System.out.println("Order removed");
    }

    public List<OrderComponent> getOrders(CompoundOrder order) { // business logic // named in class diagram: getChildren()
        return order.getOtherOrders();
    }

    public double calculateShipmentFee(CompoundOrder order) {
        List<OrderComponent> otherOrders = order.getOtherOrders();
        int numberOfProducts = 0;
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder) {
                numberOfProducts += ((SimpleOrder) otherOrder).getProducts().size();
            } else if (otherOrder instanceof CompoundOrder) {
                numberOfProducts += calculateShipmentFee((CompoundOrder) otherOrder);
            }
        }
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product

        // Calculate the total fee
        double totalFee = baseFee + (feePerProduct * numberOfProducts);

        return totalFee;
    }

    public boolean shipOrder(CompoundOrder order) {
        // store shipping fee
        double fee = calculateShipmentFee(order);
        order.setShippingFees(fee);
        List<Customer> customers = new ArrayList<>();
        List<OrderComponent> otherOrders = order.getOtherOrders();
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder) {
                customers.add(((SimpleOrder) otherOrder).getCustomer());
            } else if (otherOrder instanceof CompoundOrder) {
                customers.add(((CompoundOrder) otherOrder).getCustomer());
            }
        }
        // check if the balance of the customer is enough
        for (Customer customer : customers) {
            if (customer.getBalance() < (order.getShippingFees() / customers.size())) {
                return false;
            }
        }
        // deduct the shipping fee from the customer's balance
        for (Customer customer : customers) {
            customer.setBalance(customer.getBalance() - (order.getShippingFees() / customers.size()));
        }
        return true;
    }
}
