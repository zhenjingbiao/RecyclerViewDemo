package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/19.
 * 功能：下拉刷新，左滑删除，自定义间距，自定义下划线,多种布局显示
 **/

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.jingbiaozhen.recyclerviewdemo.view.LinearItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearRVActivity extends AppCompatActivity
{
    @BindView(R.id.linear_rv)
    RecyclerView mLinearRv;

    private List<String> mLinearData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_rv);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mLinearRv.setLayoutManager(layoutManager);
        LinearItemDecoration itemDecoration = new LinearItemDecoration(this, LinearLayoutManager.VERTICAL);
         mLinearRv.addItemDecoration(itemDecoration);
        LinearRvAdapter adapter = new LinearRvAdapter(this, mLinearData);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mLinearRv);

        mLinearRv.setAdapter(adapter);
    }

    private void initData()
    {
        for (int i = 0; i < 30; i++)
        {
            mLinearData.add("线性布局数据" + i);
        }
    }
}
