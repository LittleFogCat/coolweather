package com.lfc.coolweather2.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jjy on 2017/2/23.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}
