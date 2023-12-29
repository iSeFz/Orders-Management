package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;

public class ShipMessageSecondTemplateServiceService implements ShipMessageTemplateService {
    ShipMessageSecondTemplateModel shipMessageSecondTemplateModel;
    public ShipMessageSecondTemplateServiceService(ShipMessageSecondTemplateModel shipMessageSecondTemplateModel) {
        this.shipMessageSecondTemplateModel = shipMessageSecondTemplateModel;
    }
    public void setShipMessageSecondTemplate(ShipMessageSecondTemplateModel shipMessageSecondTemplateModel) {
        this.shipMessageSecondTemplateModel = shipMessageSecondTemplateModel;
    }
    public ShipMessageSecondTemplateModel getShipMessageSecondTemplate() {
        return shipMessageSecondTemplateModel;
    }
    @Override
    public String SendMessage() {
        return ("Hello " + shipMessageSecondTemplateModel.getCustomerName()  + ", we're delighted to inform you that your order. We appreciate your choice and look forward to serving you. Thank you for choosing our services!");
    }
}
