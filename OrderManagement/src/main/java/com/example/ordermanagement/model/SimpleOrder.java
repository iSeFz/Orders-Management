package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder implements com.example.ordermanagement.model.OrderComponent{
    private List<Product> products;
    private String location;
    private double shippingFees;
    private Customer customer;
    private boolean shipped;

    public SimpleOrder(String location, double shippingFees, Customer customer, boolean shipped) {
        this.products = new ArrayList<>();
        this.location = location;
        this.shippingFees = shippingFees;
        this.customer = customer;
        this.shipped = shipped;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String listDetails() { // Not Worked Yet
        return null;
    }

    public String getLocation() {
        return location;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public boolean isShipped() {
        return shipped;
    }

    public List<Product> getProducts() {
        return products;
    }
}
