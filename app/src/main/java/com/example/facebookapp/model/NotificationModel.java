package com.example.facebookapp.model;

public class NotificationModel {
    private String avaNotification;
    private String textNotification;

    public NotificationModel(String avaNotification, String textNotification) {
        this.avaNotification = avaNotification;
        this.textNotification = textNotification;
    }

    public String getAvaNotification() {
        return avaNotification;
    }

    public void setAvaNotification(String avaNotification) {
        this.avaNotification = avaNotification;
    }

    public String getTextNotification() {
        return textNotification;
    }

    public void setTextNotification(String textNotification) {
        this.textNotification = textNotification;
    }
}
