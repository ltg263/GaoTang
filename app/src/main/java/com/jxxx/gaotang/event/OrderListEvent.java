package com.jxxx.gaotang.event;

public class OrderListEvent {
    String orderType;

    public OrderListEvent() {
    }

    public OrderListEvent(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
