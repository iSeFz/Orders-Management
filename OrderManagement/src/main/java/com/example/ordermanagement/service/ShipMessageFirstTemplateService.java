package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;

public class ShipMessageFirstTemplateService implements ShipMessageTemplate {
    ShipMessageFirstTemplate shipMessageFirstTemplate;
    public ShipMessageFirstTemplateService() {
        this.shipMessageFirstTemplate = shipMessageFirstTemplate;
    }
    public void setShipMessageFirstTemplate(ShipMessageFirstTemplate shipMessageFirstTemplate) {
        this.shipMessageFirstTemplate = shipMessageFirstTemplate;
    }
    public ShipMessageFirstTemplate getShipMessageFirstTemplate() {
        return shipMessageFirstTemplate;
    }
    @Override
    public String SendMessage() {
        String ProductList =  shipMessageFirstTemplate.getOrderList().listDetails();
        return "Dear " + shipMessageFirstTemplate.getCustomerName() + ", your booking of the " + ProductList + " is confirmed. thanks for using our store :)";
    }
}