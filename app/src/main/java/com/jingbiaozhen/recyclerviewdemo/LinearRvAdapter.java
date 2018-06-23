package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/19.
 **/

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearRvAdapter extends RecyclerView.Adapter<LinearRvAdapter.ViewHolder> implements ItemTouchHelperAdapter
{
    private List<String> mLinearRvData;

    private Context mContext;

    public LinearRvAdapter(Context context, List<String> linearRvData)
    {
        mContext = context;
        mLinearRvData = linearRvData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        //View view = View.inflate(mContext, R.layout.item_linear, null);
        // 此处不能使用 LayoutInflater.from(mContext).inflate(R.layout.item_linear,null);
        // View.inflate(mContext, R.layout.item_linear, parent);否则会使布局失效成wrap_content
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_linear, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String itemData = mLinearRvData.get(position);
        if (!TextUtils.isEmpty(itemData))
        {
            holder.itemTv.setText(itemData);
        }

    }

    @Override
    public int getItemCount()
    {
        return mLinearRvData == null ? 0 : mLinearRvData.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition)
    {
        Collections.swap(mLinearRvData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position)
    {
        mLinearRvData.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.linear_item_tv)
        TextView itemTv;

        @BindView(R.id.delete_tv)
        TextView deleteTv;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
