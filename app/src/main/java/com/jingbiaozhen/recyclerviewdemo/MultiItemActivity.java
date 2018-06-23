package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/210.
 **/

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.jingbiaozhen.recyclerviewdemo.bean.ItemBean;
import com.jingbiaozhen.recyclerviewdemo.callback.MultiCallback;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MultiItemActivity extends AppCompatActivity
{
    private static final String TAG = "MultiItemActivity";

    private static final int ITEM_TYPE_ODD = 0;

    private static final int ITEM_TYPE_EVEN = 1;

    @BindView(R.id.multi_rv)
    RecyclerView mMultiRv;

    private List<ItemBean.ResultsBean> mGridData;

    private MultiItemAdapter mAdapter;

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
        mMultiRv.addItemDecoration(itemDecoration);
        mMultiRv.setLayoutManager(gridLayoutManager);

        mAdapter = new MultiItemAdapter(this, mGridData, new IMultiItemType<ItemBean.ResultsBean>()
        {
            @Override
            public int getLayoutId(int itemType)
            {

                if (ITEM_TYPE_ODD == itemType)
                {

                    return R.layout.item_multi;
                }
                else
                {
                    return R.layout.item_multi_even;
                }
            }

            @Override
            public int getItemViewType(int position, ItemBean.ResultsBean resultsBean)
            {
                if ((position & 1) == 0)
                {
                    return ITEM_TYPE_ODD;
                }
                else
                {

                    return ITEM_TYPE_EVEN;
                }

            }
        });
        mMultiRv.setAdapter(mAdapter);

    }

    private void initData()
    {
        OkHttpUtils.get().url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1").build().execute(new MultiCallback()
        {
            @Override
            public void onError(Call call, Exception e, int id)
            {
                Toast.makeText(MultiItemActivity.this, "" + e, Toast.LENGTH_SHORT).show();
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
