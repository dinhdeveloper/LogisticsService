package com.dinh.logistics.model;

public class PushNotificationData {
    private String title;
    private String type;
    private String data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PushNotificationData(String title, String type, String data) {
        this.title = title;
        this.type = type;
        this.data = data;
    }
}
