package com.example.ordermanagement.model;

// Abstract class to represent an order
public abstract class OrderComponent {
    private String location;
    private int orderId;
    private Customer customer;
    private double totalCost;
    private double shippingFees;
    private boolean isShipped;

    // Abstract method to list order details, acts as a getter
    public abstract String listDetails();

    public String getLocation() {
        return this.location;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int id) {
        orderId = id;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getShippingFees() {
        return this.shippingFees;
    }

    public boolean isShipped() {
        return this.isShipped;
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

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }
}
