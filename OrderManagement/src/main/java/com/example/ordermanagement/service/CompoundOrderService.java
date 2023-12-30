package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;
import org.springframework.stereotype.Service;
import com.example.ordermanagement.model.NotificationManagerModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompoundOrderService {
    NotificationManagerModel notificationManagerModel = new NotificationManagerModel();
    /*public CompoundOrderService(NotificationManagerModel notificationManagerModel) {
        this.notificationManagerModel = notificationManagerModel;
    }*/
    public String listDetails() { // business logic
        return "Order details";
    }

    public void addOrder() { // business logic // named in class diagram: addChild()
        System.out.println("Order added");
    }

    public void removeOrder() { // business logic // named in class diagram: removeChild()
        System.out.println("Order removed");
    }

    public List<OrderComponent> getOrders(CompoundOrder order) { // business logic // named in class diagram: getChildren()
        return order.getOtherOrders();
    }

    public double calculateShipmentFee(CompoundOrder order) {
        List<OrderComponent> otherOrders = order.getOtherOrders();
        int numberOfProducts = 0;
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder)
                numberOfProducts += ((SimpleOrder) otherOrder).getProducts().size();
        }
        double baseFee = 10.0; // A hypothetical base fee
        double feePerProduct = 5.0; // A hypothetical fee per product

        // Calculate the total fee
        double totalFee = baseFee + (feePerProduct * numberOfProducts);

        return totalFee;
    }

    public String shipOrder(CompoundOrder order) {
        // store shipping fee
        double fee = calculateShipmentFee(order);
        order.setShippingFees(fee);
        List<Customer> customers = new ArrayList<>();
        List<OrderComponent> otherOrders = order.getOtherOrders();

        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder)
                customers.add(((SimpleOrder) otherOrder).getCustomer());
        }

        // check if the balance of the customer is enough
        boolean noBalance = false;
        for (Customer customer : customers) {
            if (customer.getBalance() < (order.getShippingFees() / customers.size())) {
                noBalance = true;
            }
        }

        if (noBalance)
            return "No Balance Enough to Pay Shipping Fees";

        // deduct the shipping fee from the customer's balance
        for (Customer customer : customers) {
            customer.setBalance(customer.getBalance() - (order.getShippingFees() / customers.size()));
        }

        // get shipment notification
        NotificationManagerService notificationManagerService = new NotificationManagerService();
        notificationManagerService.setNotificationManagerModel(notificationManagerModel);
        String message = notificationManagerService.getMessage(order.getOrderId());

        // remove all notifications of all orders in the compound order
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder) {
                notificationManagerService.removeNotification(otherOrder.getOrderId());
            }
        }

        // remove notification from queue
        notificationManagerService.removeNotification(order.getOrderId());

        return message;
    }
}
