package com.example.ordermanagement.service;

import com.example.ordermanagement.model.*;

public class EmailNotificationService extends NotificationService {
    public EmailNotificationService(NotificationModel notificationModel) {
        super(notificationModel);
    }
    @Override
    public String doSendNotifcation() {
        return "Email Notification: " + notificationModel.getTemplate().SendMessage();
    }
}
