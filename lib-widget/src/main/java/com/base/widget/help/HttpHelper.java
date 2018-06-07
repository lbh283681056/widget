package com.base.widget.help;

import com.base.widget.inf.ILazyLoad;
import com.base.widget.inf.ILoadData;
import com.base.widget.inf.IloadViewResult;

/**
 * Created by linbinghuang
 * Date 2016/1/15
 * 含网络加载视图控制类(含页面切换)
 */
public class HttpHelper implements ILazyLoad, IloadViewResult {

    private boolean isLoad = true;
    private ILoadData mLoadData;
    private LoadViewHelper mLoadHelper;

    public HttpHelper(LoadViewHelper.ILoadHelper iLoadHelper, ILoadData mLoadData) {
        this.mLoadHelper = new LoadViewHelper(iLoadHelper);
        this.mLoadData = mLoadData;

    }
    @Override
    public boolean firstdata() {
        if (isLoad) {
            mLoadHelper.onLoadStart();
            if (mLoadData != null) {
                mLoadData.loadData(1);
            }
            return true;
        }
        return false;
    }

    @Override
    public void loadData(int page) {
        if (mLoadData != null) {
            mLoadData.loadData(page);
        }
    }

    @Override
    public void onLoadStart() {
        isLoad = true;
        mLoadHelper.onLoadStart();
    }

    @Override
    public void onLoadFailed() {
        isLoad = true;
        mLoadHelper.onLoadFailed();
    }

    @Override
    public void onLoadEmpty() {
        isLoad = true;
        mLoadHelper.onLoadEmpty();
    }

    @Override
    public void onLoadSuccess() {
        isLoad = false;
        mLoadHelper.onLoadSuccess();
    }
}
