package com.qiang.my_okhttp_utils.convert;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * 将数据 转换成Json 字符串  或者  map 数组
 */
public class HttpParams {


    private Map map = new HashMap<String, String>();

    public HttpParams() {

    }

    public HttpParams(String key, String value) {
        map.put(key, value);
    }

    public HttpParams put(String key, String value) {
        map.put(key, value);

        return this;
    }

    public String converJson() {

        return new Gson().toJson(map);
    }

    public Map buildMap() {

        return map;
    }
}
