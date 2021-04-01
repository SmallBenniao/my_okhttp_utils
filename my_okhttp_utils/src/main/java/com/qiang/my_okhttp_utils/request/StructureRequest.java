package com.qiang.my_okhttp_utils.request;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class StructureRequest {


    public StructureRequest() {

    }

    public Request postJson(String url, String bodyJson) {

        return postJson(url, bodyJson, null);
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


    /**
     *   form  post 表单请求
     * @param url       网络请求地址
     * @param formMap   字段的组合
     * @return
     */
    public Request postForm(String url, HashMap<String, String> formMap) {


        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if (formMap != null) {
            for (Map.Entry<String, String> entry : formMap.entrySet()) {
                formBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        Request build = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
                .build();

        return build;
    }

    public void get(String url){

        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();
    }

}
