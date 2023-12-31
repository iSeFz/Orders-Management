package com.example.ordermanagement.model;

import java.util.ArrayList;
import java.util.List;

// Customer entity class
public class Customer {
    private String username;
    private String email;
    private String password;
    private double balance;
    private List<Customer> friends;
    private List<OrderComponent> orders;

    // Default constructor
    public Customer() {
        this.username = "";
        this.email = "";
        this.password = "";
        this.balance = 0.0;
        this.orders = new ArrayList<>();
    }

    // Parameterized constructor
    public Customer(String username, String email, String password, double balance) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    // Add a new order to the list of orders
    public void addOrder(OrderComponent order) {
        this.orders.add(order);
    }

    // Add a new friend to the list of friends
    public void addFriend(Customer friend) {
        this.friends.add(friend);
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public List<Customer> getFriends() {
        return friends;
    }
    
    public List<OrderComponent> getOrders() {
        return orders;
    }
}
