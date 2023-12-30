package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

// Compound Order class
public class CompoundOrder extends OrderComponent {
    // List of other orders
    private List<OrderComponent> otherOrders;

    // Default Constructor
    public CompoundOrder() {
        super();
        this.otherOrders = new ArrayList<>();
    }

    // Parametrized Constructor
    public CompoundOrder(Customer customer) {
        this();
        this.setCustomer(customer);
    }

    // Add order to the list of orders
    public OrderComponent addOrder(OrderComponent order) {
        this.otherOrders.add(order);
        return order;
    }
    
    // Get other orders within the compound order
    public List<OrderComponent> getOtherOrders() {
        return otherOrders;
    }

    // List order details
    @Override
    public String listDetails() {
        String details = "";
        for(OrderComponent o : otherOrders){
            details += o.listDetails();
        }
        return details;
    }
}
