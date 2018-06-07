package com.base.widget.adapter;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 * 
 * 作者 linbinghuang
 *
 * 属性 <T>
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter implements
		IAdapterHelp<T> {

	protected Context mContext;
	protected List<T> mData;

	public BaseAdapter(Context context) {
		this.mContext = context;
	}

	public BaseAdapter(Context context, List<T> data) {
		this.mContext = context;
		this.mData = data;
	}

	public void setData(List<T> list) {
		if (list != null && list.size() > 0) {
			mData = list;
		}
	}

	public void clear() {
		if (mData != null) {
			mData.clear();
		}
	}

	public int getCount() {
		return (mData == null || mData.isEmpty()) ? 0 : mData.size();
	}

	public T getItem(int position) {
		if (mData == null) {
			return null;
		}
		return mData.get(position);
	}

	public List<T> getData() {
		return mData;
	}

	public void removeData(int position) {
		if (mData != null && !mData.isEmpty()) {
			mData.remove(position);
			notifyDataSetChanged();
		}
	}

	@Override
	public void addDataNotifyDataSetChanged(T t) {
		if (mData == null) {
			mData = new ArrayList<T>();
		}
		if (t != null) {
			mData.add(t);
			notifyDataSetChanged();
		}

	}

	@Override
	public void addBatchDataNotifyDataSetChanged(List<T> ts) {

		if (mData == null) {
			mData = new ArrayList<T>();
		}
		if (ts != null && !ts.isEmpty()) {
			mData.addAll(ts);
			notifyDataSetChanged();
		}

	}

	public void prependDataNotifyDataSetChanged(T t) {
		if (mData == null) {
			mData = new ArrayList<T>();
		}
		if (t != null) {
			mData.add(0, t);
			notifyDataSetChanged();
		}
	}

	@Override
	public void prependBatchDataNotifyDataSetChanged(List<T> ts) {
		if (mData == null) {
			mData = new ArrayList<T>();
		}
		if (ts != null && !ts.isEmpty()) {
			mData.addAll(0, ts);
			notifyDataSetChanged();
		}
	}

	@Override
	public void addDataNotifyDataSetChanged(int position, T t) {

		if (mData == null) {
			mData = new ArrayList<T>();
		}
		if (t != null) {
			mData.add(position, t);
			notifyDataSetChanged();
		}
	}

	public void notifyDataSetChanged(List<T> data) {
		if (data == null) {
			data = new ArrayList<T>();
		}
		this.mData = data;
		this.notifyDataSetChanged();
	}

}
