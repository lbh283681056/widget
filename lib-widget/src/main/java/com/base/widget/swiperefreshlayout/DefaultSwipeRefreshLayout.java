package com.base.widget.swiperefreshlayout;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.base.widget.inf.ILoadViewState;


/**
 * 默认下拉刷新
 * 作者 linbinghuang
 */
public  class DefaultSwipeRefreshLayout extends SwipeRefreshLayout
        implements ILoadViewState {

    protected ILoadViewState mChildView;
    /**
     * 返回一个合适的阀值 过了就给滑动
     */
    private int mTouchSlop;
    /**
     * 上一次触摸时的X坐标
     */
    private float mPrevX;

    public DefaultSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    //一般是ListView或recycleView
    public void setChildView(ILoadViewState mChildView) {
        this.mChildView = mChildView;
    }

    @Override
    public void onLoadFailed() {
        setRefreshing(false);
        if (mChildView != null)
            mChildView.onLoadFailed();
    }

    @Override
    public void onLoadSuccess() {
        setRefreshing(false);
        if (mChildView != null)
            mChildView.onLoadSuccess();
    }

    @Override
    public void onLoadComplete() {
        setRefreshing(false);
        if (mChildView != null)
            mChildView.onLoadComplete();
    }

    @Override
    public void onLoadEmpty() {
        setRefreshing(false);
        if (mChildView != null)
            mChildView.onLoadEmpty();
    }

    @Override
    public void onLoadStart() {
        setRefreshing(false);
        if (mChildView != null)
            mChildView.onLoadStart();

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
