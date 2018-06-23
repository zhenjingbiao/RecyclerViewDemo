package com.jingbiaozhen.recyclerviewdemo.view;
/*
 * Created by jingbiaozhen on 2018/6/19.
 **/

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LinearItemDecoration extends RecyclerView.ItemDecoration
{
    private Paint mPaint;

    private Context mContext;

    private int mOrientation;

    private int mItemSize = 1;// 若无设置，默认为一像素

    public LinearItemDecoration(Context context, int orientation)
    {
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL)
        {
            // 方向有误，默认竖直布局
            mOrientation = LinearLayoutManager.VERTICAL;
        }
        else
        {
            mOrientation = orientation;
        }
        mContext = context;

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setItemSize(int itemSize)
    {
        mItemSize = itemSize;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state)
    {
        //此处先与itemView的绘制
        if (mOrientation == LinearLayoutManager.VERTICAL)
        {
            drawVertical(c, parent);
        }
        else
        {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent)
    {
        int top = parent.getPaddingTop();
        int bottom = parent.getMeasuredHeight()-parent.getPaddingBottom();
        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount; i++)
        {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mItemSize;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent)
    {

        int left = parent.getPaddingLeft();
        int right = parent.getMeasuredWidth()-parent.getPaddingRight();
        int itemCount = parent.getChildCount();
        for (int i = 0; i < itemCount-1; i++)
        {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mItemSize;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        if(mOrientation==LinearLayoutManager.VERTICAL){
            //这四个值将影响itemView的padding
            outRect.set( 0,0,0,mItemSize);
        }else {
            outRect.set(0,0,mItemSize,0);
        }
    }
}
