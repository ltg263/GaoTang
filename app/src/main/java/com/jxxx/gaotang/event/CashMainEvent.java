package com.jxxx.gaotang.event;

public class CashMainEvent {
    String status;

    public CashMainEvent() {
    }

    public CashMainEvent(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
