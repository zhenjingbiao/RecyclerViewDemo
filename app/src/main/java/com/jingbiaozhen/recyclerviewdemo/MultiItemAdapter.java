package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import java.util.List;

import android.content.Context;

import com.jingbiaozhen.recyclerviewdemo.base.BaseHolder;
import com.jingbiaozhen.recyclerviewdemo.base.BaseMultiItemAdapter;
import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;

public class MultiItemAdapter extends BaseMultiItemAdapter<ItemBean.ResultsBean>
{
    private static final int ITEM_TYPE_ODD = 0;

    private static final int ITEM_TYPE_EVEN = 1;

    public MultiItemAdapter(Context context, List<ItemBean.ResultsBean> datas,
            IMultiItemType<ItemBean.ResultsBean> multiItemType)
    {
        super(context, datas, multiItemType);
    }

    @Override
    protected void bindData(BaseHolder holder, ItemBean.ResultsBean itemBean, int position)
    {
        int itemType = mMultiItemType.getItemViewType(position, itemBean);
        if (itemType == ITEM_TYPE_ODD)
        {
            holder.setText(R.id.multi_item_tv, itemBean.getDesc());
            holder.setImageUrl(R.id.multi_item_iv, itemBean.getUrl());
        }
        else if (itemType == ITEM_TYPE_EVEN)
        {
            holder.setImageUrl(R.id.item_multi_even_iv, itemBean.getUrl());
        }

    }
}
