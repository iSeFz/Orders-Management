package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Integer, List<Product>> getOrderFromCompound() {
        Map<Integer, List<Product>> orderMap = new HashMap<>();

        for (OrderComponent order : otherOrders) {
            SimpleOrder simpleOrder = (SimpleOrder)order;
            orderMap.put(simpleOrder.getOrderId(), simpleOrder.getProducts());
        }
        return orderMap;
    }

    // get simple order customer
    public Customer getCustomerByID(Integer orderId) {
        for(OrderComponent o : otherOrders){
            if(o.getOrderId() == orderId){
                return o.getCustomer();
            }
        }
        return null;
    }
}
