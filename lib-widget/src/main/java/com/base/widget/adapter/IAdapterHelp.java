package com.base.widget.adapter;

import java.util.List;

public interface IAdapterHelp<T> {
     void addDataNotifyDataSetChanged(int position, T t);

    /**
     * 添加数据
     */
     void addDataNotifyDataSetChanged(T t);

    /**
     * 批量添加数据
     */
     void addBatchDataNotifyDataSetChanged(List<T> ts);

    /**
     * 往前添加单个数据
     */
     void prependDataNotifyDataSetChanged(T t);

    /**
     * 往前添加数据
     */
     void prependBatchDataNotifyDataSetChanged(List<T> ts);

    /**
     * 清除数据
     */
     void clear();

    /**
     * 获取数据数
     */
     int getCount();

    /**
     * 获取单个数据
     */
     T getItem(int position);

    /**
     * 获取所有的数据
     */
     List<T> getData();

    /**
     * 更新数据
     */
     void notifyDataSetChanged(List<T> data);

    /**
     * 移除单个数据
     */
     void removeData(int position);


}
