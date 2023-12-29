package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;

public class ShipMessageFirstTemplateServiceService implements ShipMessageTemplateService {
    ShipMessageFirstTemplateModel shipMessageFirstTemplateModel;
   public ShipMessageFirstTemplateServiceService(ShipMessageFirstTemplateModel shipMessageFirstTemplateModel) {
        this.shipMessageFirstTemplateModel = shipMessageFirstTemplateModel;
    }
    public void setShipMessageFirstTemplate(ShipMessageFirstTemplateModel shipMessageFirstTemplateModel) {
        this.shipMessageFirstTemplateModel = shipMessageFirstTemplateModel;
    }
    public ShipMessageFirstTemplateModel getShipMessageFirstTemplate() {
        return shipMessageFirstTemplateModel;
    }
    @Override
    public String SendMessage() {
        String ProductList =  shipMessageFirstTemplateModel.getOrderList().listDetails();
        return "Dear " + shipMessageFirstTemplateModel.getCustomerName() + ", your booking of the " + ProductList + " is confirmed. thanks for using our store :)";
    }
}
