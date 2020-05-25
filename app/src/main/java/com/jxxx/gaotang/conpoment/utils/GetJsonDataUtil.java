package com.jxxx.gaotang.conpoment.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jxxx.gaotang.entity.AreaListDTO;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetJsonDataUtil {

    public class TempBean{
        public String id;
        public String name;
        public TempBean(String id, String name){
            this.id = id;
            this.name = name;
        }
    }

    private String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public Map<String, List> getAreaSelectData(Context context/*, String jsonData*/) {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        Map<String, List> areaMap = new HashMap<>();

        ArrayList<AreaListDTO> options1Items = new ArrayList<>();
        ArrayList<ArrayList<TempBean>> options2Items = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<TempBean>>> options3Items = new ArrayList<>();
        ArrayList<ArrayList<String>> options2Items1 = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String>>> options3Items1 = new ArrayList<>();


        String JsonData = getJson(context, "newprovince.json");//获取assets目录下的json文件数据

        ArrayList<AreaListDTO> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<TempBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<String> CityList1 = new ArrayList<>();//该省的城市列表（第二级）
            //ArrayList<String> CityIDList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<TempBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            ArrayList<ArrayList<String>> Province_AreaList1 = new ArrayList<>();//该省的所有地区列表（第三极）


            for (int c = 0; c < jsonBean.get(i).getC().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getC().get(c).getN();
                String CityID = jsonBean.get(i).getC().get(c).getID();
                CityList.add(new TempBean(CityID,CityName));//添加城市
                CityList1.add(CityName);

                ArrayList<TempBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                ArrayList<String> City_AreaList1 = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getC().get(c).getN() == null || jsonBean.get(i).getC().get(c).getD().size() == 0) {
                    City_AreaList.add(new TempBean("",""));
                    City_AreaList1.add("");
                } else {
                    for (int d = 0; d < jsonBean.get(i).getC().get(c).getD().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getC().get(c).getD().get(d).getN();
                        String AreaID = jsonBean.get(i).getC().get(c).getD().get(d).getID();
                        City_AreaList.add(new TempBean(AreaID,AreaName));//添加该城市所有地区数据
                        City_AreaList1.add(AreaName);

                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                Province_AreaList1.add(City_AreaList1);

            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);
            options2Items1.add(CityList1);



            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
            options3Items1.add(Province_AreaList1);


        }
        areaMap.put("province", options1Items);
        areaMap.put("city", options2Items);
        areaMap.put("area", options3Items);
        areaMap.put("city1", options2Items1);
        areaMap.put("area1", options3Items1);
        return areaMap;
    }

    private ArrayList<AreaListDTO> parseData(String result) {//Gson 解析
        ArrayList<AreaListDTO> detail = new ArrayList<>();
        try {
//            Gson gson = new Gson();
//            detail = (ArrayList<NewAreaListDTO>) gson.fromJson(result,NewAreaListDTO.class);
            detail = jsonToArrayList(result, AreaListDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
}

