package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderComponent;
import org.springframework.stereotype.Service;

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

    public List<OrderComponent> getOrders() { // business logic // named in class diagram: getChildren()
        return null;
    }
}
