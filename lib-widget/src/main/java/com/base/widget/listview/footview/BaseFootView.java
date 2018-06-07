package com.base.widget.listview.footview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.base.widget.inf.ILoadViewState;
import com.base.widget.listview.inf.IListViewLoadCallBack;


/**
 * 底部基类
 * 作者 linbinghuang
 *
 */
public abstract class BaseFootView extends LinearLayout implements
		ILoadViewState {
	/**
	 * ListView回调
	 */
	protected IListViewLoadCallBack mCallBack;

	public BaseFootView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseFootView(Context context) {
		super(context);
	}

	public void setCallBack(IListViewLoadCallBack mCallBack) {
		this.mCallBack = mCallBack;
	}

}
