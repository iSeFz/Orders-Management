package com.example.ordermanagement.model;

public class Notification {
    MessageTemplate message;
    public Notification(MessageTemplate message) {
        this.message = message;
    }
    public void setTemplate(MessageTemplate message) {
        this.message = message;
    }
    public MessageTemplate getTemplate() {
        return message;
    }

}
