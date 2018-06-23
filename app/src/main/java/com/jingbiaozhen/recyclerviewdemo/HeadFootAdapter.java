package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/22.
 **/


import java.util.List;

import android.content.Context;

import com.jingbiaozhen.recyclerviewdemo.base.BaseHeadFootAdapter;
import com.jingbiaozhen.recyclerviewdemo.base.BaseHolder;
import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;

public class HeadFootAdapter extends BaseHeadFootAdapter<ItemBean.ResultsBean> {

    public HeadFootAdapter(Context context, List<ItemBean.ResultsBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseHolder holder, ItemBean.ResultsBean itemBean, int position) {
        holder.setText(R.id.multi_item_tv, itemBean.getDesc());
        holder.setImageUrl(R.id.multi_item_iv, itemBean.getUrl());
    }


}
