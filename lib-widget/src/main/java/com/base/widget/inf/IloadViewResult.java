package com.base.widget.inf;

/**
 * Created by linbinghuang
 * Date 2016/1/15
 * 页面加载结果
 *
 */
public interface IloadViewResult {
    void onLoadStart();
    void onLoadFailed();
    void onLoadEmpty();
    void onLoadSuccess();
}
