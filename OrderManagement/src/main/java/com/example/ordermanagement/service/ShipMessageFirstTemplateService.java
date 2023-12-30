package com.example.ordermanagement.service;
import com.example.ordermanagement.model.*;

import java.util.List;

public class ShipMessageFirstTemplateService implements ShipMessageTemplateService {
    ShipMessageFirstTemplateModel shipMessageFirstTemplateModel;
   public ShipMessageFirstTemplateService(ShipMessageFirstTemplateModel shipMessageFirstTemplateModel) {
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
        List<Product> OrderList  =  shipMessageFirstTemplateModel.getOrderList();
        String ProductList = "";
        for (int i = 0; i < OrderList.size(); i++) {
            ProductList += OrderList.get(i).getName();
            if (i != OrderList.size() - 1) {
                ProductList += ", ";
            }
        }
        return "Dear " + shipMessageFirstTemplateModel.getCustomerName() + ", your booking of the " + ProductList + " is confirmed. thanks for using our store :)";
    }
}
