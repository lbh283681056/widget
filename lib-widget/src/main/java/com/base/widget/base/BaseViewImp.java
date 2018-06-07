package com.base.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.base.widget.inf.IInitView;

/**
 * Created by linbinghuang on 2016/12/1.
 */
public abstract class BaseViewImp<T extends IBasePresenter> extends RelativeLayout implements IInitView,IBaseView {
    T mP;

    public BaseViewImp(Context context) {
        super(context);
    }

    public BaseViewImp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mP.subscribe();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mP!=null) {
            mP.unsubscribe();
        }
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
