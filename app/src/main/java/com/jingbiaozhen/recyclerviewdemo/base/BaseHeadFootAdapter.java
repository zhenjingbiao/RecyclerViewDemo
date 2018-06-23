package com.jingbiaozhen.recyclerviewdemo.base;
/*
 * Created by jingbiaozhen on 2018/6/22.
 **/

import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseHeadFootAdapter<T> extends BaseRecycleAdapter<T>
{
    private static final int TYPE_HEADER = 0x1001;

    private static final int TYPE_FOOTER = 0x1002;

    private static final int TYPE_NORMAL = 0x1003;

    private View mHeadView;

    private View mFootView;

    public BaseHeadFootAdapter(Context context, List<T> datas, int layoutId)
    {
        super(context, datas, layoutId);

    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        BaseHolder holder;
        if (viewType == TYPE_HEADER)
        {
            mHeadView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            holder = new HeaderHolder(mHeadView, parent.getContext());
        }
        else if (viewType == TYPE_FOOTER)
        {
            mFootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            holder = new FooterHolder(mFootView, parent.getContext());
        }
        else
        {
            holder = super.onCreateViewHolder(parent, viewType);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position)
    {
        if (holder instanceof HeaderHolder)
        {
            return;
        }
        else if (holder instanceof FooterHolder)
        {
            return;
        }

        bindData(holder, getItem(position), position);
        // super.onBindViewHolder(holder, position);

    }

    @Override
    public int getItemCount()
    {
        int count = mDatas == null ? 0 : mDatas.size();
        return count + (mHeadView == null ? 0 : 1) + (mFootView == null ? 0 : 1);
    }

    @Override
    public int getItemViewType(int position)
    {
        int headerCount = mHeadView == null ? 0 : 1;
        if (position < headerCount)
        {
            return TYPE_HEADER;
        }
        else if (isItemView(position))
        {
            return TYPE_NORMAL;

        }
        return TYPE_FOOTER;
    }

    public T getItem(int position)
    {
        if (position < 0 || position > getItemCount() || mDatas == null)
        {
            return null;
        }
        int headCount = mHeadView == null ? 0 : 1;
        if (position < headCount)
        {
            return null;
        }
        position -= headCount;
        if (position >= mDatas.size())
        {
            return null;
        }
        return mDatas.get(position);
    }

    /**
     * 判断当前View是否是内容View，而非HeadView or FooterView
     */
    private boolean isItemView(int position)
    {
        if (mDatas != null)
        {
            int headerCount = mHeadView == null ? 0 : 1;
            return position < (mDatas.size() + headerCount);
        }
        return false;
    }

    public void addHeadView(View header)
    {
        mHeadView = header;
    }

    public void addFootView(View footer)
    {
        mFootView = footer;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseHolder holder)
    {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null)
        {
            if ((layoutParams instanceof StaggeredGridLayoutManager.LayoutParams)
                    && ((holder instanceof HeaderHolder) || (holder instanceof FooterHolder)))
            {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                params.setFullSpan(true);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null || !(manager instanceof GridLayoutManager))
        {
            return;
        }
        final GridLayoutManager gridManager = ((GridLayoutManager) manager);
        gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                int type = getItemViewType(position);
                if (type == TYPE_FOOTER || type == TYPE_HEADER)
                {
                    return gridManager.getSpanCount();
                }
                return 1;
            }
        });
    }

    public static class FooterHolder extends BaseHolder
    {

        public FooterHolder(View itemView, Context context)
        {
            super(itemView, context);
        }
    }

    public static class HeaderHolder extends BaseHolder
    {

        public HeaderHolder(View itemView, Context context)
        {
            super(itemView, context);
        }
    }
}
