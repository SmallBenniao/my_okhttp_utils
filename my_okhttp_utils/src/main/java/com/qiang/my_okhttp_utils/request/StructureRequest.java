package com.qiang.my_okhttp_utils.request;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class StructureRequest {


    public StructureRequest() {

    }

    public Request postJson(String url, String bodyJson, HashMap<String, String> headerMap) {

        RequestBody create = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), bodyJson);

        Headers.Builder headerBuilder = new Headers.Builder();
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                headerBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        Request builder = new Request.Builder()
                .headers(headerBuilder.build())
                .post(create)
                .url(url)
                .build();


        return builder;
    }

}
