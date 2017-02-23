package com.lfc.coolweather2.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jjy on 2017/2/23.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;
    }
}
