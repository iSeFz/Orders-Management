package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder implements com.example.ordermanagement.model.OrderComponent{
    private List<OrderComponent> otherOrders;
    private String location;
    private double shippingFees;
    private Customer customer;

    public CompoundOrder(String location, double shippingFees, Customer customer) {
        this.otherOrders = new ArrayList<>();
        this.location = location;
        this.shippingFees = shippingFees;
        this.customer = customer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public List<OrderComponent> getOtherOrders() {
        return otherOrders;
    }

    @Override
    public String listDetails() { // Not Worked Yet
        return null;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public double getShippingFees() {
        return shippingFees;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}
