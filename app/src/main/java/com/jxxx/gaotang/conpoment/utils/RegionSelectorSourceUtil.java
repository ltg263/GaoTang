package com.jxxx.gaotang.conpoment.utils;

import android.app.Application;

import java.util.Map;

/**
 * 作者：wzh
 * 创建时间：2018/4/27 11:35
 * QianDai
 */

public class RegionSelectorSourceUtil {

    private Thread thread;
    private static Map areaMap;

    private static RegionSelectorSourceUtil instance;
    private Application application;

    public static synchronized RegionSelectorSourceUtil getInstance(Application application) {
        if (instance == null) {
            instance = new RegionSelectorSourceUtil(application);
        }
        return instance;
    }

    private RegionSelectorSourceUtil(Application application){
        this.application = application;
        if (areaMap == null){
            initAreaData();
        }
    }

    private synchronized void initAreaData(){
        if (thread == null){
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 写子线程中的操作,解析省市区数据
                    areaMap = new GetJsonDataUtil().getAreaSelectData(application);
                }
            });
            thread.start();
        }
    }

    public static Map getAreaMap() {
        return areaMap;
    }
}
