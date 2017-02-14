package com.coolweather.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response != null && response.isSuccessful()) {
                data = response.body().string();
                Log.e(TAG, "onResponse: "+data);
                provinceList = HttpUtil.parseJsonWithJSONObject(data);
                init();
            }
        }
    };
    String data;
    List<Province> provinceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUtil.getProvinceData(Constants.URL_PROVINCE_LIST, callback);


    }

    void init() {
        final ListView listView = (ListView) findViewById(R.id.list_province);
        final Adapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, provinceList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter((ListAdapter) adapter);
            }
        });
    }
}
