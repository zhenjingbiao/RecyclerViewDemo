package com.jingbiaozhen.recyclerviewdemo.callback;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/


import android.util.Log;

import com.google.gson.Gson;
import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

public abstract class MultiCallback extends Callback<ItemBean> {
    private static final String TAG = "MultiCallback";
    @Override
    public ItemBean parseNetworkResponse(Response response, int id) throws Exception {

        String result=response.body().string();
        Log.d(TAG, "parseNetworkResponse: result"+result);
        ItemBean itemBean=new Gson().fromJson(result,ItemBean.class);
        Log.d(TAG, "parseNetworkResponse: itemBean"+itemBean.toString());
        return itemBean;
    }
}
