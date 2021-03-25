package com.qiang.my_okhttp_utils.response;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qiang.my_okhttp_utils.client.StructureOkHttpClient;
import com.qiang.my_okhttp_utils.dialog.StructureDialog;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class StructureCallback<T> implements Callback {


    private Handler mHandler;
    private Class<T> mClass;
    private String mCallName;

    public StructureCallback(Class<T> mClass) {
        this.mClass = mClass;
        this.mHandler = new Handler(Looper.myLooper());
    }

    public void setName(String callName) {
        this.mCallName = callName;
    }


    //失败
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                StructureDialog.cancelLoading();
                onFailure(e.toString());
                //删除
                StructureOkHttpClient.removeCall(mCallName, call);
            }
        });
    }

    //成功
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


        mHandler.post(new Runnable() {
            @Override
            public void run() {
                StructureDialog.cancelLoading();
                response(response.body().toString());
                //删除
                StructureOkHttpClient.removeCall(mCallName, call);
            }
        });
    }

    //成功 数据的处理
    private void response(String response) {

        if (TextUtils.isEmpty(response) || response.trim().equals("")) {
            onFailure("json为空");
        } else {

            try {
                if (mClass == null) {
                    onFailure("转换失败");
                } else {
                    T t = new Gson().fromJson(response, mClass);
                    if (t != null) {
                        onResponse(t);
                    } else {
                        onFailure("转换完，对象为空");
                    }
                }
            } catch (Exception e) {
                onFailure(e.toString());
                e.printStackTrace();
            }
        }
    }

    //成功的回调
    protected abstract void onResponse(T t);

    //失败的回调
    protected abstract void onFailure(String str);
}
