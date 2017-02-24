package com.lfc.coolweather2.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.lfc.coolweather2.db.City;
import com.lfc.coolweather2.db.County;
import com.lfc.coolweather2.db.Province;
import com.lfc.coolweather2.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jjy on 2017/2/21.
 */

public class Utility {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject object = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(object.getInt("id"));
                    province.setProvinceName(object.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject object = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityName(object.getString("name"));
                    city.setCityCode(object.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else return false;
    }

    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject object = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                return false;
            }
        } else return false;
    }

    /**
     * 传入json数据,返回实例化后的Weather对象
     *
     * @param responseData 传入的json数据
     * @return 实例化后的Weather对象
     */
    public static Weather handleWeatherResponse(String responseData) {
        try {
            // 将整个json实例化保存在jsonObject中
            JSONObject jsonObject = new JSONObject(responseData);
            // 从jsonObject中取出键为"HeWeather"的数据,并保存在数组中
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            // 取出数组中的第一项,并以字符串形式保存
            String weatherContent = jsonArray.getJSONObject(0).toString();
            // 返回通过Gson解析后的Weather对象
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
