package com.example.ordermanagement.service;

import com.example.ordermanagement.model.CompoundOrder;
import com.example.ordermanagement.model.OrderComponent;

public interface OrderComponentService {
    public OrderComponent getCertainOrder(String customerName, Integer orderID);
    public String getOrderDetails(String customerName, Integer orderID);
    public boolean deductTotalCost(String customerName, Integer orderID);
    public boolean deductShippingFees(String customerName, Integer orderID);

    // public String listAllOrders();
}
