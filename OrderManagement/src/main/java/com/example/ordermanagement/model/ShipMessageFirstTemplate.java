package com.example.ordermanagement.model;
import java.util.List;
public class ShipMessageFirstTemplate{
    private String CustomerName;
    private OrderComponent OrderList;
    public ShipMessageFirstTemplate(){
        this.OrderList = null;
    }
    public ShipMessageFirstTemplate(String customerName, OrderComponent orderList) {
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