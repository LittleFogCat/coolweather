package com.coolweather.android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jjy on 2017/2/15.
 */

class HttpUtil {

    static void getProvinceData(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    static List<Province> parseJsonWithJSONObject(String data) {
        List<Province> provinceList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            Log.e(TAG, "parseJsonWithJSONObject: arraylength =  " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int id = object.getInt("id");
                String name = object.getString("name");
                Province province = new Province();
                province.setId(id);
                province.setProvinceName(name);
                provinceList.add(province);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "parseJsonWithJSONObject: listsize = " + provinceList.size());
        return provinceList;
    }

    private static final String TAG = "HttpUtil";
}
