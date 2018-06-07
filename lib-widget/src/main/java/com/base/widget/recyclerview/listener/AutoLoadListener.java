package com.base.widget.recyclerview.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.base.widget.inf.ILoadViewState;
import com.base.widget.recyclerview.inf.INextLoadCallBack;

/**
 * Created by linbinghuang on 2016/3/5.
 */
public class AutoLoadListener extends RecyclerView.OnScrollListener implements ILoadViewState {
    private INextLoadCallBack mCallBack;
    //到第几项加载
    private int mPreparaIndex ;
    private boolean isLoadingMore;

    int[] lastPositions;

    public AutoLoadListener(INextLoadCallBack mCallBack, int preparaIndex) {
        this.mCallBack = mCallBack;
        this.mPreparaIndex = preparaIndex;
    }

    public AutoLoadListener(INextLoadCallBack mCallBack) {
        this.mCallBack = mCallBack;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        RecyclerView.LayoutManager mLayoutManager = recyclerView.getLayoutManager();
//        int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        int lastVisibleItem = 0;
        if (mLayoutManager instanceof LinearLayoutManager){
            lastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();
        }else if (mLayoutManager instanceof StaggeredGridLayoutManager){
            //另外方法来判断
            if (lastPositions == null) {
                lastPositions = new int[((StaggeredGridLayoutManager)mLayoutManager).getSpanCount()];
            }
            ((StaggeredGridLayoutManager)mLayoutManager).findLastVisibleItemPositions(lastPositions);
            lastVisibleItem = findMax(lastPositions);
        }
        int totalItemCount = mLayoutManager.getItemCount();
        //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
        // dy>0 表示向下滑动
        if (lastVisibleItem >= totalItemCount - mPreparaIndex && dy > 0) {
            if (!isLoadingMore) {
                if (mCallBack != null) {
                    mCallBack.loadNextPage();
                }
                isLoadingMore = true;
                Log.e("waterFall", "onScrolled: isLoadingMore==true" );
            } else {
                Log.i("FFF", "哥正在加载别吵哥");
            }
        }
    }


    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
    @Override
    public void onLoadFailed() {
        isLoadingMore = false;
    }

    @Override
    public void onLoadSuccess() {
        isLoadingMore = false;
    }

    @Override
    public void onLoadComplete() {
        isLoadingMore = false;
    }

    @Override
    public void onLoadEmpty() {
        isLoadingMore = false;
    }

    @Override
    public void onLoadStart() {
    }

}
