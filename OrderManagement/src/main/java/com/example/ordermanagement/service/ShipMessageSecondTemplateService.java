package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;

public class ShipMessageSecondTemplateService implements ShipMessageTemplateService {
    ShipMessageSecondTemplateModel shipMessageSecondTemplateModel;

    public ShipMessageSecondTemplateService(ShipMessageSecondTemplateModel shipMessageSecondTemplateModel) {
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
        return ("Hello " + shipMessageSecondTemplateModel.getCustomerName()
                + ", we're delighted to inform you that your order is shipped & on the way to you.\n"
                + "We appreciate your choice and look forward to serving you. Thanks for choosing our services!");
    }
}
