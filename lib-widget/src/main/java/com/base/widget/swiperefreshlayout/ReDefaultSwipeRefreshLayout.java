package com.base.widget.swiperefreshlayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.base.widget.inf.ILoadViewState;
import com.base.widget.listview.inf.IListViewLoadCallBack;
import com.base.widget.listview.listview.BaseListView;


/**
 * 默认下来刷新
 * 
 * 作者 linbinghuang
 * 属性 <T>
 * 
 */
public abstract class ReDefaultSwipeRefreshLayout extends SwipeRefreshLayout
		implements ILoadViewState {

	protected BaseListView mListView;
	/**
	 * 返回一个合适的阀值 过了就给滑动
	 */
	private int mTouchSlop;
	/**
	 * 上一次触摸时的X坐标
	 */
	private float mPrevX;

	public ReDefaultSwipeRefreshLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	/**
	 * 初始化
	 */
	public abstract void init();

	public BaseListView getListView() {
		return mListView;
	}

	/**
	 * 设置头部
	 * 
	 * 属性 View
	 */
	public void addHeaderView(View headView) {
		mListView.addHeaderView(headView);

	}

	/**
	 * 设置适配器
	 * 
	 * 属性 adapter
	 */
	public void setAdapter(BaseAdapter adapter) {
		mListView.setAdapter(adapter);
	}

	/**
	 * 上下拉刷新
	 * 
	 * 属性 callBack
	 * 属性 onRefreshListener
	 */
	public void setUpAndDownRefresh(IListViewLoadCallBack callBack,
			OnRefreshListener onRefreshListener) {
		if (callBack != null) {
			mListView.setIListViewCallBack(callBack);
		}
		if (onRefreshListener != null) {
			setOnRefreshListener(onRefreshListener);
		}
	}


	/**
	 * 预加载
	 * 属性 pageSize
	 * 属性 beforLoadNum
	 */
	public void prepareLoad(int pageSize, int beforLoadNum) {
		mListView.prepareLoad(pageSize, beforLoadNum);
	}
	/**
	 * 设置点击事件
	 * 属性 clickListener
	 */
	public void setOnItemClickListener(OnItemClickListener clickListener) {
		if (clickListener != null)
			mListView.setOnItemClickListener(clickListener);
	}

	@Override
	public void onLoadFailed() {
		setRefreshing(false);
		mListView.onLoadFailed();
	}

	@Override
	public void onLoadSuccess() {
		setRefreshing(false);
		mListView.onLoadSuccess();
	}

	@Override
	public void onLoadComplete() {
		setRefreshing(false);
		mListView.onLoadComplete();
	}

	@Override
	public void onLoadEmpty() {
		setRefreshing(false);
		mListView.onLoadEmpty();
	}

	@Override
	public void onLoadStart() {
		setRefreshing(false);
		mListView.onLoadStart();

	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPrevX = event.getX();
			break;

		case MotionEvent.ACTION_MOVE:
			final float eventX = event.getX();
			float xDiff = Math.abs(eventX - mPrevX);
			// 增加60的容差，让下拉刷新在竖直滑动时就可以触发
			if (xDiff > mTouchSlop + 60) {
				return false;
			}
		}
		return super.onInterceptTouchEvent(event);
	}
}
