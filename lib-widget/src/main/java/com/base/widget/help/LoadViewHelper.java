package com.base.widget.help;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.base.widget.inf.IloadViewResult;

/**
 * Created by linbinghuang
 * Date 2016/1/15
 * 控制网络访问的页面切换
 */
public class LoadViewHelper implements IloadViewResult {
    private View mLoadingView;
    private View mLoadFailedView;
    private View mContentView;
    private View mLoadEmptyView;

    public LoadViewHelper(ILoadHelper loadhelper) {
        mContentView = loadhelper.getContentView();
        mLoadingView = loadhelper.getLoadingView(mContentView);
        mLoadFailedView = loadhelper.getLoadFailedView(mContentView);
        mLoadEmptyView = loadhelper.getLoadEmptyView(mContentView);
    }
    private Handler mHandler = new Handler(Looper.getMainLooper()) {

        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_EMPTY:
                    mLoadingView.setVisibility(View.GONE);
                    mLoadFailedView.setVisibility(View.GONE);
                    mContentView.setVisibility(View.GONE);
                    mLoadEmptyView.setVisibility(View.VISIBLE);
                    break;
                case ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_FAILED:
                    mLoadingView.setVisibility(View.GONE);
                    mLoadFailedView.setVisibility(View.VISIBLE);
                    mContentView.setVisibility(View.GONE);
                    mLoadEmptyView.setVisibility(View.GONE);
                    break;
                case ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_FINISH:
                    mLoadingView.setVisibility(View.GONE);
                    mLoadFailedView.setVisibility(View.GONE);
                    mContentView.setVisibility(View.VISIBLE);
                    mLoadEmptyView.setVisibility(View.GONE);
                    break;
                case ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_START:
                    mLoadingView.setVisibility(View.VISIBLE);
                    mLoadFailedView.setVisibility(View.GONE);
                    mContentView.setVisibility(View.GONE);
                    mLoadEmptyView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };

    public interface ILoadHelper {
        /**
         * 加载开始
         */
        int MESSAGE_ID_COMMON_LOADDATA_START = 0;
        /**
         * 加载为空
         */
       int MESSAGE_ID_COMMON_LOADDATA_EMPTY = 1;
        /**
         * 加载失败
         */
         int MESSAGE_ID_COMMON_LOADDATA_FAILED = 2;
        /**
         * 加载结束
         */
       int MESSAGE_ID_COMMON_LOADDATA_FINISH = 3;

        View getContentView();

        View getLoadingView(View contentView);

        View getLoadFailedView(View contentView);

        View getLoadEmptyView(View contentView);
    }

    @Override
    public void onLoadStart() {
        mHandler.sendEmptyMessage(ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_START);
    }
    @Override
    public void onLoadFailed() {
        mHandler.sendEmptyMessage(ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_FAILED);
    }
    @Override
    public void onLoadEmpty() {
        mHandler.sendEmptyMessage(ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_EMPTY);
    }
    @Override
    public void onLoadSuccess() {
        mHandler.sendEmptyMessage(ILoadHelper.MESSAGE_ID_COMMON_LOADDATA_FINISH);
    }
}
