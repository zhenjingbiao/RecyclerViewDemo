package com.jingbiaozhen.recyclerviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/*
 * Created by jingbiaozhen on 2018/6/20.
 **/

public class SlidingButtonView extends HorizontalScrollView {
    public SlidingButtonView(Context context) {
        this(context,null);
    }

    public SlidingButtonView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOverScrollMode(OVER_SCROLL_NEVER);//设置滑动到尽头不显示光晕
    }
}
