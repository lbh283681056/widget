package com.base.widget.webView.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.base.widget.inf.IInitView;


/**
 * Created by linbinghuang on 2016/8/28.
 */
public abstract class BaseWebView extends WebView  implements IInitView {
    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        initDataBeforView();
        initView();
        initData();
        initListener();
    }

    @Override
    public void initDataBeforView() {

    }
}
