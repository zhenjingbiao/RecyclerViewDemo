package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/20.
 **/

public interface ItemTouchHelperAdapter
{
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
