package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderComponent;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public OrderComponent createOrder() { // make busniss logic
        return null;
    }

    public boolean shipOrder(OrderComponent order) { // make busniss logic
        return false;
    }

    public void deductBalance(double amount) { // make busniss logic
    }

    public void deductShippingFees(double amount) { // make busniss logic
    }
}
