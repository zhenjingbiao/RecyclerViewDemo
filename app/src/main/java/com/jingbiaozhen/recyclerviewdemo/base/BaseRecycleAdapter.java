package com.jingbiaozhen.recyclerviewdemo.base;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseHolder>
{

    protected Context mContext;

    protected List<T> mDatas;

    private int mLayoutId;

    protected LayoutInflater mLayoutInflater;

    public BaseRecycleAdapter(Context context, List<T> datas, int layoutId)
    {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        return new BaseHolder(mLayoutInflater.inflate(mLayoutId, parent, false), parent.getContext());

    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position)
    {

        bindData(holder, mDatas.get(position), position);
    }

    protected abstract void bindData(BaseHolder holder, T t, int position);

    public void setData(List<T> datas)
    {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return mDatas == null ? 0 : mDatas.size();
    }
}
