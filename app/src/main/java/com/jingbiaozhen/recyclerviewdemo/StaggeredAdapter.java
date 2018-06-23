package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import java.util.List;

import android.content.Context;

import com.jingbiaozhen.recyclerviewdemo.base.BaseHeadFootAdapter;
import com.jingbiaozhen.recyclerviewdemo.base.BaseHolder;
import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;

public class StaggeredAdapter extends BaseHeadFootAdapter<ItemBean.ResultsBean>
{
    private static final String TAG = "StaggeredAdapter";
    public StaggeredAdapter(Context context, List<ItemBean.ResultsBean> datas, int layoutId)
    {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseHolder holder, ItemBean.ResultsBean resultsBean, int position) {
        holder.setImageUrl(R.id.staggered_item_iv,resultsBean.getUrl());
    }


}
