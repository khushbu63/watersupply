package com.example.android.watersupply;

/**
 * Created by Anonymous on 4/23/2018.
 */

public class Modal {
    private String username;
    private String topic;
    private String detail;

    public Modal(String username, String topic, String detail) {
        this.username = username;
        this.topic = topic;
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public String getTopic() {
        return topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}


