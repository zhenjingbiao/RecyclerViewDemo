package com.jingbiaozhen.recyclerviewdemo.base;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.jingbiaozhen.recyclerviewdemo.IMultiItemType;

public abstract class BaseMultiItemAdapter<T> extends BaseRecycleAdapter<T>
{

    protected IMultiItemType<T> mMultiItemType;

    public BaseMultiItemAdapter(Context context, List<T> datas, IMultiItemType<T> multiItemType)
    {
        super(context, datas, -1);
        mMultiItemType = multiItemType;
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        int layoutId = mMultiItemType.getLayoutId(viewType);
        return BaseHolder.getViewHolder(mContext, parent, layoutId);

    }

    @Override
    public int getItemViewType(int position)
    {

        return mMultiItemType.getItemViewType(position, mDatas.get(position));

    }
}
