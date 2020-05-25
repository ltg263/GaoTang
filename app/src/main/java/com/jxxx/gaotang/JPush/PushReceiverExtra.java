package com.jxxx.gaotang.JPush;

public class PushReceiverExtra {

    String content;
    String createTime;
    String id;
    String type;
    String typeName;
    String userId;
    String type2;
    String type3;
    String orderId;
    String orderType;
    String productId;
    String productType;
    String informationId;

    public String getInformationId() {
        return informationId;
    }

    public void setInformationId(String informationId) {
        this.informationId = informationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getType3() {
        return type3;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "PushReceiverExtra{" +
                "content=" + content +
                ", createTime=" + createTime +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", typeName=" + typeName +
                ", userId=" + userId +
                ", type2=" + type2 +
                ", type3=" + type3 +
                ", orderId=" + orderId +
                ", orderType=" + orderType +
                ", productId=" + productId +
                ", productType=" + productType +
                '}';
    }
}
