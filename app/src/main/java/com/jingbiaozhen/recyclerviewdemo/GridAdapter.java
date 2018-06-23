package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/21.
 **/

import java.util.List;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.jingbiaozhen.recyclerviewdemo.base.BaseRecycleAdapter;
import com.jingbiaozhen.recyclerviewdemo.base.BaseHolder;

public class GridAdapter extends BaseRecycleAdapter<String>
{
    private static final String TAG = "GridAdapter";
    public GridAdapter(Context context, List<String> datas, int layoutId)
    {
        super(context, datas, layoutId);
    }

    @Override
    protected void bindData(BaseHolder holder, final String s, int position)
    {
        holder.setText(R.id.item_grid_tv, s);
        holder.setOnClickListener(R.id.item_grid_tv, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(mContext, "点击了" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
