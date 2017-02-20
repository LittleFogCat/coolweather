package com.coolweather.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.coolweather.android.db.Province;

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
                String data = response.body().string();
                provinceList = HttpUtil.parseJsonWithJSONObject(data);
                if (provinceList != null && provinceList.size() > 0) {
                    init();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            provinceAdapter.add(new Province(99999, "出错了"));
                        }
                    });
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpUtil.getProvinceData(getResources().getString(R.string.url_list_province), callback);
    }

    List<Province> provinceList;
    ListView listView;
    ArrayAdapter<Province> provinceAdapter;
    Spinner spProvince, spCity;

    void init() {
        listView = (ListView) findViewById(R.id.list_province);
        spProvince = (Spinner) findViewById(R.id.sp_province);
        spCity = (Spinner) findViewById(R.id.sp_city);
        spProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });

        provinceAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        provinceAdapter.addAll(provinceList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spProvince.setAdapter(provinceAdapter);
            }
        });
    }
}
