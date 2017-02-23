package com.lfc.coolweather2.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jjy on 2017/2/23.
 */

public class Forcast {
    String date;

    @SerializedName("cond")
    public More more;

    @SerializedName("tmp")
    public Temperature temperature;

    public class More {
        @SerializedName("txt_d")
        public String info;
    }

    public class Temperature {
        public String max;
        public String min;
    }
}
