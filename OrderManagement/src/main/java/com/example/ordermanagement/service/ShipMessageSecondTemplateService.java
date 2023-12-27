package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;

public class ShipMessageSecondTemplateService implements ShipMessageTemplate {
    ShipMessageSecondTemplate shipMessageSecondTemplate ;
    public ShipMessageSecondTemplateService(ShipMessageSecondTemplate shipMessageSecondTemplate) {
        this.shipMessageSecondTemplate = shipMessageSecondTemplate;
    }
    public void setShipMessageSecondTemplate(ShipMessageSecondTemplate shipMessageSecondTemplate) {
        this.shipMessageSecondTemplate = shipMessageSecondTemplate;
    }
    public ShipMessageSecondTemplate getShipMessageSecondTemplate() {
        return shipMessageSecondTemplate;
    }
    @Override
    public String SendMessage() {
        return ("Hello " +shipMessageSecondTemplate.getCustomerName()  + ", we're delighted to inform you that your order. We appreciate your choice and look forward to serving you. Thank you for choosing our services!");
    }
}
