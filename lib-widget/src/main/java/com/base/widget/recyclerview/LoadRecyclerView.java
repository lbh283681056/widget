package com.base.widget.recyclerview;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.base.widget.inf.ILoadViewState;
import com.base.widget.recyclerview.inf.INextLoadCallBack;
import com.base.widget.recyclerview.listener.AutoLoadListener;


/**
 * Created by linbinghuang on 2016/3/5.
 */
public class LoadRecyclerView extends ItemClickRecyclerView implements ILoadViewState {
    private AutoLoadListener autoLoadListener;
    private ILoadViewState mFootView;

    public LoadRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LoadRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadRecyclerView(Context context) {
        super(context);
    }


    public void addLoadFootView(View v){
        if(v instanceof ILoadViewState){
            addFootView(v);
        }else{
            Log.i("FFF","骚年,你加脚的方式不正确");
        }
    }

    @Override
    public void onLoadFailed() {
        if (autoLoadListener != null) {
            autoLoadListener.onLoadFailed();
        }
        if (mFootView != null) {
            mFootView.onLoadFailed();
        }
    }

    @Override
    public void onLoadSuccess() {
        if (autoLoadListener != null) {
            autoLoadListener.onLoadSuccess();
        }
        if (mFootView != null) {
            mFootView.onLoadSuccess();
        }
    }

    @Override
    public void onLoadComplete() {
        if (autoLoadListener != null) {
            autoLoadListener.onLoadComplete();
        }
        if (mFootView != null) {
            mFootView.onLoadComplete();
        }
    }

    @Override
    public void onLoadEmpty() {
        if (autoLoadListener != null) {
            autoLoadListener.onLoadEmpty();
        }
        if (mFootView != null) {
            mFootView.onLoadEmpty();
        }
    }

    @Override
    public void onLoadStart() {
        if (autoLoadListener != null) {
            autoLoadListener.onLoadStart();
        }
        if (mFootView != null) {
            mFootView.onLoadStart();
        }
    }

    //如果需要预加载就用这个
    public void preparaLoad(INextLoadCallBack mCallBack, int preparaIndex) {
        autoLoadListener = new AutoLoadListener(mCallBack, preparaIndex);
        addOnScrollListener(autoLoadListener);
    }


}
