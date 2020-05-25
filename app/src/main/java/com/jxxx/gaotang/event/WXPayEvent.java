package com.jxxx.gaotang.event;

public class WXPayEvent {
    String pay_status;

    public WXPayEvent() {
    }

    public WXPayEvent(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
}
