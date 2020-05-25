package com.jxxx.gaotang.event;

public class MainEvent {
    String status;

    public MainEvent() {
    }

    public MainEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
