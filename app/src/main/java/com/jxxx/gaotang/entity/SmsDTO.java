package com.jxxx.gaotang.entity;

public class SmsDTO {

    public SmsDTO(String name, String phoneNumber, String smsbody, String date, String type){
        setName(name);
        setPhoneNumber(phoneNumber);
        setSmsbody(smsbody);
        setDate(date);
        setType(type);
    }
    private String name;
    private String phoneNumber;
    private String smsbody;
    private String date;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSmsbody() {
        return smsbody;
    }

    public void setSmsbody(String smsbody) {
        this.smsbody = smsbody;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
