package com.jingbiaozhen.recyclerviewdemo;
/*
 * Created by jingbiaozhen on 2018/6/21.
 * RecyclerView中多种布局显示
 **/

public interface IMultiItemType<T>
{
    /**
     * 根据item的类型返回LayoutId
     * 
     * @param itemType itemType的类型
     */
    int getLayoutId(int itemType);

    /**
     * 根据item的位置返回类型
     * @param position item的位置
     * @param t 这个位置绑定的数据
     */
    int getItemViewType(int position, T t);
}
