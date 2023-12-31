package com.example.ordermanagement.service;
import java.util.*;

import com.example.ordermanagement.model.*;
import com.example.ordermanagement.repos.NotificationManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.ordermanagement.repos.CustomersRepo;
import com.example.ordermanagement.repos.ProductsRepo;
@Service
public class CustomerService {
    // Customer & Product repos to be injected
    @Autowired
    private final CustomersRepo customersRepo;
    @Autowired
    private final NotificationManagerRepo notificationManagerRepo;

    private final ProductsRepo productsRepo;
    // Order Service Instance
    @Qualifier("simpleOrderService")
    @Autowired
    private final OrderComponentService orderService1;
    @Qualifier("compoundOrderService")
    @Autowired
    private final OrderComponentService orderService2;

    // Constructor to inject the repo
    public CustomerService(CustomersRepo customersRepo, NotificationManagerRepo notificationManagerRepo, ProductsRepo productsRepo, @Qualifier("simpleOrderService") OrderComponentService orderService1 , @Qualifier("compoundOrderService") OrderComponentService orderService2) {
        this.customersRepo = customersRepo;
        this.notificationManagerRepo = notificationManagerRepo;
        this.productsRepo = productsRepo;
        this.orderService1 = orderService1;
        this.orderService2 = orderService2;
    }

    // Create a new account for a customer & add it to the list of customers
    public Customer addCustomer(String username, String email, String password, double balance) {
        Customer newCustomer = new Customer(username, email, password, balance);
        return customersRepo.addCustomer(newCustomer);
    }


    // Place a simple order & assign it to its customer with the list of products given
    public String placeSimpleOrder(String customerName, List<Integer> listOfProductSerials) {
        // Get the customer from the repo
        Customer customer = customersRepo.getCustomer(customerName);
        // If the customer doesn't exist, return null
        if (customer == null)
            return "Customer NOT found!";
        // List of products to get from the repo
        List<Product> products = new ArrayList<Product>();
        // Get the products from the repo
        for (Integer serialNum : listOfProductSerials) {
            Product product = productsRepo.getProduct(serialNum);
            // Add the product to the list of customer order products
            if (product != null) {
                // If the product is available, add it to the list
                products.add(product);
                // Decrement the remaining count of the product
                product.setRemainingCount(product.getRemainingCount() - 1);
            }
        }
        // Create a new order
        SimpleOrder newOrder = new SimpleOrder(customer);
        // Add the products to the order
        for (Product product : products)
            newOrder.addProduct(product);
        // Set the order id
        newOrder.setOrderId(generateOrderID());
        // Add the order to the list of customer orders
        customer.addOrder(newOrder);
        // Deduct the order cost from the customer's balance, return the newly created order
        /////////////////////////////////////////////////////////////////
        //Placment Message
        PlacmentMessageTemplateService placmentMessageTemplateService = new PlacmentMessageFirstTemplateService();
        MessageTemplateService messageTemplateService =placmentMessageTemplateService;
        NotificationModel Model =new NotificationModel(messageTemplateService,newOrder.getOrderId());
        NotificationService notificationService = new EmailNotificationService(Model);
        String Message = notificationService.doSendNotifcation();
        /////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////
        //Ship Message
        ShipMessageFirstTemplateModel shipMessageFirstTemplateModel = new ShipMessageFirstTemplateModel();
        shipMessageFirstTemplateModel.setCustomerName(customerName);
        shipMessageFirstTemplateModel.setOrderList(products);
        MessageTemplateService MessageTemp = new ShipMessageFirstTemplateService(shipMessageFirstTemplateModel);
        NotificationModel Model2 = new NotificationModel(MessageTemp,newOrder.getOrderId());
        NotificationService notificationService2 = new EmailNotificationService(Model2);
        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        notificationManagerService.addNotification(notificationService2);
        ///////////////////////////////////////
        if (orderService1.deductTotalCost(customerName, newOrder.getOrderId()))
            // return newOrder;
            return Message + "\nSimple Order ID #" + newOrder.getOrderId();
        // Otherwise, return null
        return "Insufficient customer balance";
    }

    public String placeCompoundOrder(String customerName, List<Integer> listOfProductSerials, Map<String, Integer> listOfFriendOrders) {
        // Get the customer from the repo
        Customer customer = customersRepo.getCustomer(customerName);
        // If the customer doesn't exist, return null
        if (customer == null)
            return "Customer NOT found!";

        // make compound order
        CompoundOrder compoundOrder = new CompoundOrder(customer);
        // Set the order id
        compoundOrder.setOrderId(generateOrderID());
        // List of products to get from the repo
        List<Product> products = new ArrayList<Product>();
        // Get the products from the repo
        for (Integer serialNum : listOfProductSerials) {
            Product product = productsRepo.getProduct(serialNum);
            // Add the product to the list of customer order products
            if (product != null) {
                // If the product is available, add it to the list
                products.add(product);
                // Decrement the remaining count of the product
                product.setRemainingCount(product.getRemainingCount() - 1);
            }
        }
        // place the order of customer
        SimpleOrder ownerOrder = new SimpleOrder(customer);
        // Add the products to the order
        for (Product product : products)
            ownerOrder.addProduct(product);
        // Set the order id
        ownerOrder.setOrderId(generateOrderID());
        // Add the order to the list of customer orders
        customer.addOrder(ownerOrder);
        compoundOrder.addOrder(ownerOrder);
        // Deduct the order cost from the owner customer's balance
        orderService1.deductTotalCost(customerName, ownerOrder.getOrderId());
        //Placment Message
        PlacmentMessageTemplateService placmentMessageTemplateService = new PlacmentMessageFirstTemplateService();
        MessageTemplateService messageTemplateService =placmentMessageTemplateService;
        NotificationModel Model =new NotificationModel(messageTemplateService,compoundOrder.getOrderId());
        NotificationService notificationService = new EmailNotificationService(Model);
        String Message= notificationService.doSendNotifcation();
        /////////////////////////////////////////////////////////////////
        //Ship Message
        ShipMessageFirstTemplateModel shipMessageFirstTemplateModel = new ShipMessageFirstTemplateModel();
        shipMessageFirstTemplateModel.setCustomerName(customerName);
        shipMessageFirstTemplateModel.setOrderList(ownerOrder.getProducts());
        MessageTemplateService MessageTemp= new ShipMessageFirstTemplateService(shipMessageFirstTemplateModel);
        NotificationModel Model2 =new NotificationModel(MessageTemp,compoundOrder.getOrderId());
        NotificationService notificationService2 = new EmailNotificationService(Model2);
        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        notificationManagerService.addNotification(notificationService2);
        ///////////////////////////////////////
        for (Map.Entry<String, Integer> entry : listOfFriendOrders.entrySet()) {
            SimpleOrder simpleOrder = (SimpleOrder) orderService1.getCertainOrder(entry.getKey(), entry.getValue());
            // Deduct the order cost from the customer's balance
            // orderService1.deductTotalCost(entry.getKey(), entry.getValue());
            // Add friend order to the list of orders
            compoundOrder.addOrder(simpleOrder);
            /////////////////////////////////////////////////////////////////
            //Ship Message
            ShipMessageFirstTemplateModel shipMessageFirstTemplateModel1 = new ShipMessageFirstTemplateModel();
            shipMessageFirstTemplateModel1.setCustomerName(entry.getKey());
            shipMessageFirstTemplateModel1.setOrderList(simpleOrder.getProducts());
            MessageTemplateService MessageTemp1= new ShipMessageFirstTemplateService(shipMessageFirstTemplateModel1);
            NotificationModel Model1 =new NotificationModel(MessageTemp1,simpleOrder.getOrderId());
            NotificationService notificationService1 = new EmailNotificationService(Model1);
            NotificationManagerService notificationManagerService1 = new NotificationManagerService(notificationManagerRepo);
            notificationManagerService1.addNotification(notificationService1);
            ///////////////////////////////////////
        }
        customer.addOrder(compoundOrder);
        return Message + "\nCompound Order ID #" + compoundOrder.getOrderId();
    }

    // Ship an existing Simple order
    public String shipSimpleOrder(String customerName, Integer orderID) {
        // Get the specified order
        SimpleOrder order = (SimpleOrder) orderService1.getCertainOrder(customerName, orderID);
        // check if customer can deducate shipping fees or not
        boolean deducateShippingFees = orderService1.deductShippingFees(customerName, orderID);

        if (!deducateShippingFees)
            return "Insufficient customer balance";
        // get shipment notification
        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        // notificationManagerService.setNotificationManagerModel(notificationManagerModel);
        String message = notificationManagerService.getMessage(order.getOrderId());
        // remove notification from queue
        notificationManagerService.removeNotification(order.getOrderId());
        return message;
    }

    public String shipCompoundOrder(String customerName, Integer orderID) {
        // Get the specified order
        CompoundOrder order = (CompoundOrder) orderService2.getCertainOrder(customerName, orderID);
        // store shipping fee
        boolean deducateShippingFees = orderService2.deductShippingFees(customerName, orderID);

        List<Customer> customers = new ArrayList<>();
        List<OrderComponent> otherOrders = order.getOtherOrders();

        // check if customer can deducate shipping fees or not
        if (!deducateShippingFees)
            return "Insufficient customer balance";

        // get shipment notification
        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        //notificationManagerService.setNotificationManagerModel(notificationManagerModel);
        String message = notificationManagerService.getMessage(orderID);
        // remove all notifications of all orders in the compound order
        for (OrderComponent otherOrder : otherOrders) {
            if (otherOrder instanceof SimpleOrder) {
                notificationManagerService.removeNotification(otherOrder.getOrderId());
            }
        }

        // remove notification from queue
        notificationManagerService.removeNotification(orderID);
        return message;
    }

    public String cancelSimpleOrder(String customerName, Integer orderID) {
        // Get the specified order
        SimpleOrder order = (SimpleOrder) orderService1.getCertainOrder(customerName, orderID);
        if (order == null)
            return "Order Doesn't Exsits";

        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        // remove notification from queue
        notificationManagerService.removeNotification(order.getOrderId());

        // calculate total cost of the order
        double orderCost = 0.0;
        for (Product product : order.getProducts()) {
            orderCost += product.getPrice();
            // increase the product count + 1 in the product repo
            productsRepo.getProduct(product.getSerialNum()).setRemainingCount(productsRepo.getProduct(product.getSerialNum()).getRemainingCount() + 1);
        }
        // add the order cost to the customer balance
        order.getCustomer().setBalance(order.getCustomer().getBalance() + orderCost);


        return "Cancel Simple order Successfully";
    }

    public String cancelCompoundOrder(String customerName, Integer orderID) {
        // Get the specified order
        CompoundOrder order = (CompoundOrder) orderService2.getCertainOrder(customerName, orderID);
        if (order == null)
            return "Order Doesn't Exsits";

        NotificationManagerService notificationManagerService = new NotificationManagerService(notificationManagerRepo);
        // remove notification from queue
        notificationManagerService.removeNotification(order.getOrderId());

        // get OrderMap
        Map<Integer, List<Product>> orderMap = order.getOrderFromCompound();

        // calculate total cost of every order
        for (Map.Entry<Integer, List<Product>> entry : orderMap.entrySet()) {
            double orderCost = 0.0;
            for (Product product : entry.getValue()) {
                orderCost += product.getPrice();
                // increase the product count + 1 in the product repo
                productsRepo.getProduct(product.getSerialNum()).setRemainingCount(productsRepo.getProduct(product.getSerialNum()).getRemainingCount() + 1);
            }
            // add the order cost to the simple order customer balance
            Customer orderCustomer = order.getCustomerByID(entry.getKey());
            orderCustomer.setBalance(orderCustomer.getBalance() + orderCost);

            // remove notification of every order in compound order
            notificationManagerService.removeNotification(entry.getKey());
        }

        return "Cancel Compound order Successfully";
    }

    // Return all system customers
    public List<Customer> getCustomers() {
        return customersRepo.getCustomers();
    }

    // Generate random order ID
    public int generateOrderID() {
        return new Random().nextInt(1001);
    }
}
