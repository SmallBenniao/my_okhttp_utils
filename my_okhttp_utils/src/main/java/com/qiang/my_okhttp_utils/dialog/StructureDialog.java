package com.qiang.my_okhttp_utils.dialog;

import android.app.ProgressDialog;
import android.content.Context;

public class StructureDialog {


    private static ProgressDialog progressDialog;


    //基本的 加载动画
    public static void shouLoading(Context mContext) {

        if (progressDialog != null) {
            //加载完毕自动关闭dialog
            progressDialog.cancel();
        }

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setIcon(android.R.mipmap.sym_def_app_icon);
        progressDialog.setTitle("加载dialog");
        progressDialog.setMessage("加载中...");
        progressDialog.setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        progressDialog.setCancelable(false);//点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
        progressDialog.show();

    }

    public static void shouLoading(Context mContext, int icon, String title) {

        if (progressDialog != null) {
            //加载完毕自动关闭dialog
            progressDialog.cancel();
        }

        progressDialog = new ProgressDialog(mContext);

        progressDialog.setIcon(icon);
        progressDialog.setTitle(title);
        progressDialog.setMessage("加载中...");
        progressDialog.setIndeterminate(true);// 是否形成一个加载动画  true表示不明确加载进度形成转圈动画  false 表示明确加载进度
        progressDialog.setCancelable(false);//点击返回键或者dialog四周是否关闭dialog  true表示可以关闭 false表示不可关闭
        progressDialog.show();

    }


    //取消基本的加载动画
    public static void cancelLoading() {

        if (progressDialog != null) {
            //加载完毕自动关闭dialog
            progressDialog.cancel();
        }
    }
}
