package com.example.ordermanagement.model;

public class ShipMessageFirstTemplateModel {
    private String CustomerName;
    private OrderComponent OrderList;
    public ShipMessageFirstTemplateModel(){
        this.OrderList = null;
    }
    public ShipMessageFirstTemplateModel(String customerName, OrderComponent orderList) {
        CustomerName = customerName;
        OrderList = orderList;
    }
    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
    public void setOrderList(OrderComponent orderList) {
        OrderList = orderList;
    }
    public String getCustomerName() {
        return CustomerName;
    }
    public OrderComponent getOrderList() {
        return OrderList;
    }
}