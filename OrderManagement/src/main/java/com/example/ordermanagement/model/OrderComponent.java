package com.example.ordermanagement.model;

public interface OrderComponent {
    public String listDetails();
    public String getLocation();
    public double getShippingFees();
    public Customer getCustomer();
}
