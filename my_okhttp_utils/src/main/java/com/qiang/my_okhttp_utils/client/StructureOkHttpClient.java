package com.qiang.my_okhttp_utils.client;

import android.content.Context;
import android.util.Log;

import com.qiang.my_okhttp_utils.dialog.StructureDialog;
import com.qiang.my_okhttp_utils.response.StructureCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 构建 okhttpClient  用来网络请求
 */
public class StructureOkHttpClient {


    private static OkHttpClient okHttpClient;

    //存储  call
    private static Map<String, List<Call>> callMap = new HashMap<>();

    static {

        //okhttp 的请求 打印
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {

                Log.e("打印", s);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);

        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });


        okHttpClient = builder.build();
    }


    public static void postJson(String name, Context mContext, boolean isDialog, Request request, StructureCallback callback) {

        //需要 request
        Call call = okHttpClient.newCall(request);
        callback.setName(name);

        //启动 加载动画
        if (isDialog) {
            StructureDialog.shouLoading(mContext);
        }

        //需要CallBack
        call.enqueue(callback);

        //添加 call
        addCall(name, call);
    }

    public static void request(String name, Context mContext, boolean isDialog, Request request, StructureCallback callback) {

        //需要 request
        Call call = okHttpClient.newCall(request);
        callback.setName(name);

        //启动 加载动画
        if (isDialog) {
            StructureDialog.shouLoading(mContext);
        }

        //需要CallBack
        call.enqueue(callback);

        //添加 call
        addCall(name, call);
    }

    //添加
    public static void addCall(String name, Call call) {

        List<Call> calls = callMap.get(name);
        if (calls == null) {
            calls = new ArrayList<>();
        }
        calls.add(call);
        callMap.put(name, calls);
    }

    //删除
    public static void removeCall(String name) {
        List<Call> calls = callMap.get(name);
        if (calls != null)
            for (int i = 0; i < calls.size(); i++) {
                Call call = calls.get(i);
                if (call != null && !call.isCanceled()) {
                    call.cancel();
                    calls.remove(i);
                    i--;
                }
            }

        callMap.remove(name);
    }

    //删除
    public static void removeCall(String name, Call call) {

        List<Call> calls = callMap.get(name);

        if (call != null && !call.isCanceled())
            call.cancel();

        if (calls != null) {
            calls.remove(call);
        }
        if (calls.size() == 0) {
            callMap.remove(name);
        }
    }

    //删除所有
    public static void removeAllCall() {

        for (int i = 0; i < callMap.size(); i++) {

            List<Call> calls = callMap.get(i);
            if (calls != null) {
                for (int j = 0; j < calls.size(); j++) {
                    Call call = calls.get(j);
                    if (call != null && !call.isCanceled()) {
                        call.cancel();
                        calls.remove(j);
                        j--;
                    }
                }
            }
        }
        callMap.clear();
    }

}
