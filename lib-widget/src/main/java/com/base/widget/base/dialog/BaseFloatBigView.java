package com.base.widget.base.dialog;

import android.content.Context;
import android.view.WindowManager;

import com.base.util.ScreenUtil;

/**
 * Created by zengyingzhi on 2016/3/30.
 */
public abstract class BaseFloatBigView extends BaseFloat {

    public BaseFloatBigView(Context context) {
        super(context);
    }

    @Override
    protected void setParams(WindowManager.LayoutParams params) {
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        int width = ScreenUtil.getCurrentScreenWidth1(getContext());
        int height = ScreenUtil.getCurrentScreenHeight1(getContext());
        mParams.width = width;
        mParams.height = height;
        //setPoint();
    }

    protected void cancle() {
//        SZFloatViewManager.getInstance().closeFloatView();
    }

    //设置x y轴
    private void setPoint() {
        int w = ScreenUtil.getCurrentScreenWidth1(getContext());
        int h = ScreenUtil.getCurrentScreenHeight1(getContext());
        int x = (w - mParams.width) / 2;
        int y = (h - mParams.height) / 2;
        mParams.x = x;
        mParams.y = y;
    }
}
