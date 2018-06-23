package com.jingbiaozhen.recyclerviewdemo.base;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BaseHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> mViewArrays;

    // private View mItemView;

    private Context mContext;

    public BaseHolder(View itemView, Context context)
    {
        super(itemView);
        // mItemView = itemView;
        mContext = context;
        mViewArrays = new SparseArray<>();
    }

    public <T extends View> T getViewById(int viewId)
    {
        View view = mViewArrays.get(viewId);
        if (view == null)
        {
            view = itemView.findViewById(viewId);
            mViewArrays.put(viewId, view);
        }
        return (T) view;
    }

    public static BaseHolder getViewHolder(Context context, ViewGroup parent, int layoutId)
    {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new BaseHolder(itemView, context);
    }

    public View getRootView()
    {
        return itemView;
    }

    public BaseHolder setOnClickListener(int viewId, View.OnClickListener onClickListener)
    {
        View view = getViewById(viewId);
        if (view != null)
        {
            view.setOnClickListener(onClickListener);
        }
        return this;
    }

    public BaseHolder setViewGone(int viewId)
    {
        View view = getViewById(viewId);
        if (view != null)
        {
            view.setVisibility(View.GONE);
        }
        return this;
    }

    public BaseHolder setViewInVisible(int viewId)
    {
        View view = getViewById(viewId);
        if (view != null)
        {
            view.setVisibility(View.INVISIBLE);
        }
        return this;
    }

    public BaseHolder setViewVisible(int viewId)
    {
        View view = getViewById(viewId);
        if (view != null)
        {
            view.setVisibility(View.VISIBLE);
        }
        return this;
    }

    public BaseHolder setText(int viewId, String text)
    {
        View textView = getViewById(viewId);
        if (textView != null && textView instanceof TextView && !TextUtils.isEmpty(text))
        {
            ((TextView) textView).setText(text);
        }
        return this;
    }

    public BaseHolder setImageRes(int viewId, int resId)
    {
        View imageView = getViewById(viewId);
        if (imageView != null && imageView instanceof ImageView)
        {
            Glide.with(mContext).load(resId).into(((ImageView) imageView));
        }
        return this;
    }

    public BaseHolder setImageUrl(int viewId, String url)
    {
        View imageView = getViewById(viewId);
        if (imageView != null && imageView instanceof ImageView)
        {
           Glide.with(mContext).load(url).into(((ImageView) imageView));
        }
        return this;
    }

}
