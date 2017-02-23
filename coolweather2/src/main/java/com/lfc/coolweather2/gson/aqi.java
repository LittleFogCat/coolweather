package com.lfc.coolweather2.gson;

/**
 * Created by jjy on 2017/2/23.
 */

public class AQI {

    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
    }
}
