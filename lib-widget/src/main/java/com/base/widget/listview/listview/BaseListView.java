package com.base.widget.listview.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.base.widget.inf.ILoadViewState;
import com.base.widget.listview.LoadStatueEnum;
import com.base.widget.listview.footview.BaseFootView;
import com.base.widget.listview.inf.IListViewLoadCallBack;


/**
 * Listview(可设置预加载)
 *
 * 作者 linbinghuang
 */
public class BaseListView extends ListView implements OnScrollListener,
        ILoadViewState {

    /**
     * 是否滑动加载
     */
    private boolean isScrollLoad = false;
    /**
     * 加载过程中的变量值
     */
    private LoadStatueEnum mStatueEnum = LoadStatueEnum.NON;

    private int mPageSize = 10;
    /**
     * 每页的第5项就加载下一页
     */
    private static int befor_load_num = 5;
    /**
     * 底部布局
     */
    private BaseFootView mFootView;

    /**
     * 页面回调
     */
    private IListViewLoadCallBack mCallBack;

    /**
     * 已经显示的项
     */
    private int mVisibledCount;
    /**
     * 总的项
     */
    private int mTotalItemCount;

    /**
     * 滚动状态
     */
    private int iScrollState;

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseListView(Context context) {
        super(context);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        setOnScrollListener(this);
    }

    /**
     * 设置回调
     *
     * 属性 callBack
     */
    public void setIListViewCallBack(IListViewLoadCallBack callBack) {
        mCallBack = callBack;
        mFootView.setCallBack(mCallBack);
    }

    /**
     * 添加basefoot底部加载
     *
     * 属性 v
     */
    public void addBaseFootView(View v) {
        addFooterView(v, null, false);
    }

    @Override
    public void addFooterView(View v, Object data, boolean isSelectable) {
        if (v instanceof BaseFootView && mFootView == null) {
            mFootView = (BaseFootView) v;
            mFootView.setCallBack(mCallBack);
        }
        super.addFooterView(v, data, isSelectable);
    }

    @Override
    public boolean removeFooterView(View v) {
        if (mFootView == v) {
            mFootView = null;
        }
        return super.removeFooterView(v);
    }

    /**
     * 移除加载的footView
     *
     * @return
     */
    public boolean removeFooterView() {
        if (mFootView != null) {
            BaseFootView view = mFootView;
            mFootView = null;
            return removeFooterView(view);
        }
        return false;
    }

    /**
     * 替换footview
     *
     * @return
     */
    public void replaceFooterView(View view) {
        removeFooterView();
        addBaseFootView(view);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        iScrollState = scrollState;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (isScrollLoad) {
            mVisibledCount = firstVisibleItem + visibleItemCount;
            mTotalItemCount = totalItemCount;
            scollLoadData();
        }

    }

    /**
     * 滑动加载
     */
    private void scollLoadData() {
        if (iScrollState == SCROLL_STATE_IDLE) {
            return;
        }
        if (mTotalItemCount >= mPageSize) {
            boolean isLoad = mTotalItemCount - mVisibledCount < befor_load_num ? true
                    : false;
            if (isLoad) {
                loadData();
            }
        }
    }

    @Override
    public void onLoadFailed() {
        mStatueEnum = LoadStatueEnum.FAILED;
        if (mFootView != null) {
            mFootView.onLoadFailed();
        }
    }

    @Override
    public void onLoadSuccess() {
        mStatueEnum = LoadStatueEnum.NON;
        if (mFootView != null) {
            mFootView.onLoadSuccess();
        }
    }

    @Override
    public void onLoadComplete() {
        mStatueEnum = LoadStatueEnum.COMPLETE;
        if (mFootView != null) {
            mFootView.onLoadComplete();
        }
    }

    @Override
    public void onLoadEmpty() {
        mStatueEnum = LoadStatueEnum.EMPTY;
        if (mFootView != null) {
            mFootView.onLoadEmpty();
        }
    }

    @Override
    public void onLoadStart() {
        mStatueEnum = LoadStatueEnum.NON;
        if (mFootView != null) {
            mFootView.onLoadStart();
        }
    }

    /**
     * 加载数据
     */
    public void loadData() {
        if (mCallBack != null && mStatueEnum != LoadStatueEnum.LOADING) {
            mStatueEnum = LoadStatueEnum.LOADING;
            mCallBack.loadNextPage();
        }
    }

    public int getPageSize() {
        return mPageSize;
    }

    public boolean isScrollLoad() {
        return isScrollLoad;
    }

    public void setScrollLoad(boolean isScrollLoad) {

        this.isScrollLoad = isScrollLoad;
    }

    /**
     * 设置是否预加载
     *
     * 属性 pageSize
     * 属性 beforLoadNum
     */
    public void prepareLoad(int pageSize, int beforLoadNum) {
        mPageSize = pageSize;
        befor_load_num = beforLoadNum;
        setScrollLoad(true);
    }

    public BaseFootView getFootView() {
        return mFootView;
    }

}
