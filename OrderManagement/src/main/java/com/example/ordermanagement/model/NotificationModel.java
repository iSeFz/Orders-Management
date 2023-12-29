package com.example.ordermanagement.model;

import com.example.ordermanagement.service.MessageTemplateService;

public class NotificationModel {
    MessageTemplateService message;
    int orderSerialNumberID;
    public NotificationModel(MessageTemplateService message , int orderSerialNumberID) {
        this.message = message;
        this.orderSerialNumberID=orderSerialNumberID;
    }
    public void setTemplate(MessageTemplateService message) {
        this.message = message;
    }
    public MessageTemplateService getTemplate() {
        return message;
    }
    public void setorderSerialNumberID (int id ){
        this.orderSerialNumberID=id;
    }
    public int getOrderSerialNumberID (){
        return this.orderSerialNumberID;
    }
}
