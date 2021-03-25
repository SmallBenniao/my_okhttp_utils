package com.qiang.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.qiang.my_okhttp_utils.client.StructureOkHttpClient;
import com.qiang.my_okhttp_utils.convert.HttpParams;
import com.qiang.my_okhttp_utils.request.StructureRequest;
import com.qiang.my_okhttp_utils.response.StructureCallback;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String bodyJson = new HttpParams("", "").converJson();

        Request request = new StructureRequest().postJson("http://www.baidu.com", bodyJson, null);

        StructureOkHttpClient.postJson("name", MainActivity.this, true, request, new StructureCallback<User>(User.class) {


            @Override
            protected void onResponse(User user) {

                Log.e("==", "cheng");
            }

            @Override
            protected void onFailure(String str) {
                Log.e("==", "bai");
            }
        });

    }

}