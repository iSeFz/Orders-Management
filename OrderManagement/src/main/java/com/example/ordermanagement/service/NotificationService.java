package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;

public abstract class NotificationService {
    NotificationModel notificationModel;
    public NotificationService(NotificationModel notificationModel) {
        this.notificationModel = notificationModel;
    }
    public void setNotification(NotificationModel notificationModel) {
        this.notificationModel = notificationModel;
    }
    public NotificationModel getNotification() {
        return notificationModel;
    }
    public abstract String doSendNotifcation();
}
