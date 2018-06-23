package com.jingbiaozhen.recyclerviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.linear_tv, R.id.grid_tv, R.id.staggeredGrid_tv, R.id.multi_tv, R.id.head_foot_tv})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.linear_tv:
                startActivity(new Intent(MainActivity.this, LinearRVActivity.class));
                break;
            case R.id.grid_tv:
                startActivity(new Intent(MainActivity.this, GridRVActivity.class));
                break;
            case R.id.staggeredGrid_tv:
                startActivity(new Intent(MainActivity.this, StaggeredActivity.class));
                break;
            case R.id.multi_tv:
                startActivity(new Intent(MainActivity.this, MultiItemActivity.class));
                break;
            case R.id.head_foot_tv:
                startActivity(new Intent(MainActivity.this, HeadFootActivity.class));
                break;
            default:
                break;

        }
    }

}
