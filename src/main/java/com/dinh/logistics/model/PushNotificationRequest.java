package com.dinh.logistics.model;

public class PushNotificationRequest {
    private String to;
    private PushNotificationData data;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public PushNotificationData getData() {
        return data;
    }

    public void setData(PushNotificationData data) {
        this.data = data;
    }
}
