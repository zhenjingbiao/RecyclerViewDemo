package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/22.
 **/

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;
import com.jingbiaozhen.recyclerviewdemo.callback.MultiCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class HeadFootActivity extends AppCompatActivity
{
    private static final String TAG = "HeadFootActivity";


    @BindView(R.id.multi_rv)
    RecyclerView mHeadFootRv;

    private List<ItemBean.ResultsBean> mGridData;

    private HeadFootAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_rv);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView()
    {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mHeadFootRv.addItemDecoration(itemDecoration);
        mHeadFootRv.setLayoutManager(gridLayoutManager);

        mAdapter = new HeadFootAdapter(this, mGridData, R.layout.item_multi);
        TextView headView = new TextView(this);
        headView.setText("这是头布局");
        TextView footView = new TextView(this);
        footView.setText("这是脚布局");
        mAdapter.addHeadView(headView);
        mAdapter.addFootView(footView);
        mHeadFootRv.setAdapter(mAdapter);

    }

    private void initData()
    {
        OkHttpUtils.get().url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1").build().execute(new MultiCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                Toast.makeText(HeadFootActivity.this, "" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(ItemBean response, int id)
            {
                Log.d(TAG, "onResponse: response" + response.toString());
                mGridData = response.getResults();
                mAdapter.setData(mGridData);
            }
        });

    }
}
