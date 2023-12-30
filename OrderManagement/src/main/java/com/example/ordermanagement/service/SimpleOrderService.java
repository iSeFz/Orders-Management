package com.example.ordermanagement.service;

import com.example.ordermanagement.Common;
import com.example.ordermanagement.model.Customer;
import com.example.ordermanagement.model.SimpleOrder;
import com.example.ordermanagement.repos.CustomersRepo;
import com.example.ordermanagement.repos.ProductsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleOrderService {
    private List<SimpleOrder> orders;
//    private Common common;
    private ProductsRepo productsRepo;
    private CustomersRepo customersRepo;
    public SimpleOrderService(){
        orders = new ArrayList<>();
        customersRepo = new CustomersRepo();
        productsRepo = new ProductsRepo();
        SimpleOrder simpleOrder = new SimpleOrder(customersRepo.getCustomers().get(1),"egypt");
        simpleOrder.setOrderId(orders.size()+1);
        orders.add(simpleOrder);
        orders.get(0).addProduct(productsRepo.getProducts().get(2));
        orders.get(0).addProduct(productsRepo.getProducts().get(3));
        orders.get(0).addProduct(productsRepo.getProducts().get(4));
        orders.get(0).addProduct(productsRepo.getProducts().get(1));
    }
    public String listDetails(int id) { // business logic
        for(SimpleOrder s : orders){
            if(s.getOrderId() == id)
                return s.listDetails();
        }
        return null;
    }

    public void addProduct() { // business logic
        System.out.println("Product added");
    }

    public double calculateCost() { // business logic
        return 100.0;
    }

    public int getRemainingCount() { // business logic
        return 10;
    }

    public double calculateShipmentFee(SimpleOrder order) {
        int numberOfProducts = order.getProducts().size();
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product

        // Calculate the total fee
        double totalFee = baseFee + (feePerProduct * numberOfProducts);

        return totalFee;
    }

    public boolean shipOrder(SimpleOrder order) {
        // store shipping fee
        double fee = calculateShipmentFee(order);
        order.setShippingFees(fee);
        // check if the balance of the customer is enough
        if (order.getCustomer().getBalance() < order.getShippingFees()) {
            return false;
        }
        // deduct the shipping fee from the customer's balance
        order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getShippingFees());
        return true;
    }
}
