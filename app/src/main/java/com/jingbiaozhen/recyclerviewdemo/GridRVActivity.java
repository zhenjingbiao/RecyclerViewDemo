package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/20.
 * 功能：下拉刷新，左滑删除，自定义间距，自定义下划线,多种布局显示
 **/

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridRVActivity extends AppCompatActivity
{
    @BindView(R.id.grid_rv)
    RecyclerView mGridRv;

    private List<String> mGridData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_rv);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView()
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        mGridRv.setLayoutManager(gridLayoutManager);

        GridAdapter adapter = new GridAdapter(this, mGridData,R.layout.item_grid);


        mGridRv.setAdapter(adapter);
    }

    private void initData()
    {
        for (int i = 0; i < 60; i++)
        {
            mGridData.add("网格布局数据" + i);
        }
    }
}
