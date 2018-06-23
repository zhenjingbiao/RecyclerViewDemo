package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/20.
 **/

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback
{

    private static final String TAG = "ItemTouchHelperCallback";
    private ItemTouchHelperAdapter mAdapter;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter)
    {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        // 此方法用来返回可以滑动的方向 从左到右，从上到下
        int dragFrags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;// 允许上下滑动
        int swipeFrags = ItemTouchHelper.LEFT;// 允许从右向左滑动
        return makeMovementFlags(dragFrags, swipeFrags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
    {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
    {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());

    }

    @Override
    public boolean isLongPressDragEnabled()
    {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return true;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder)
    {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScaleX(0);
        TextView deleteTv = ((LinearRvAdapter.ViewHolder) viewHolder).deleteTv;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) deleteTv.getLayoutParams();
        layoutParams.width = 120;
        layoutParams.height = 100;
        deleteTv.setLayoutParams(layoutParams);
        deleteTv.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY,
            int actionState, boolean isCurrentlyActive)
    {
        if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){
            int limit=getSlideLimitation(viewHolder);
            if(Math.abs(dX)<limit/2){
                viewHolder.itemView.scrollTo( 0,0);
            }
            if(Math.abs(dX)>=limit/2){
                viewHolder.itemView.scrollTo(-limit,0);
            }
        }else if(actionState==ItemTouchHelper.ANIMATION_TYPE_SWIPE_CANCEL){

            Log.d(TAG, "onChildDraw: ANIMATION_TYPE_SWIPE_CANCEL");
            
        }else if(actionState==ItemTouchHelper.ANIMATION_TYPE_SWIPE_SUCCESS){
            Log.d(TAG, "onChildDraw: ANIMATION_TYPE_SWIPE_SUCCESS");
        }
        else{

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    private int getSlideLimitation(RecyclerView.ViewHolder viewHolder)
    {
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;

        return viewGroup.getChildAt(1).getWidth();
    }
}
