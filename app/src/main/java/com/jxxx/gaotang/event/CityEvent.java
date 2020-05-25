package com.jxxx.gaotang.event;

/**
 * Created by ShiXL on 2018/8/21.
 */

public class CityEvent {
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public CityEvent(String cityName) {
        this.cityName = cityName;
    }

    String cityName;
}
