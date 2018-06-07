package com.base.widget.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.base.widget.inf.IInitView;


/**
 *
 * Created by linbinghuang on 2016/6/23.
 */
public abstract class BaseView extends RelativeLayout implements IInitView{
    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
