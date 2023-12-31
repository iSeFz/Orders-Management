package com.example.ordermanagement.model;

import java.util.List;

public class ShipMessageFirstTemplateModel {
    private String CustomerName;
    private List<Product> OrderList;

    public ShipMessageFirstTemplateModel() {
        this.OrderList = null;
    }

    public ShipMessageFirstTemplateModel(String customerName, List<Product> OrderList) {
        CustomerName = customerName;
        this.OrderList = OrderList;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public void setOrderList(List<Product> OrderList) {
        this.OrderList = OrderList;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public List<Product> getOrderList() {
        return OrderList;
    }
}