package com.base.widget.base.dialog;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.base.widget.base.dialog.inf.IFloat;
import com.base.widget.inf.IInitView;


/**
 * Created by linbinghuang
 * Date 2015/10/22
 */
public abstract class BaseFloat extends RelativeLayout implements IFloat, IInitView {
    protected WindowManager mWindowManager;
    protected WindowManager.LayoutParams mParams;
    protected boolean isAddWm = false;
    protected int orientation;

    public BaseFloat(Context context) {
        super(context);
        init();
    }

    private void init() {
        initView();
        initData();
        initListener();
    }

    public BaseFloat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseFloat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addFloat() {
        if (!isAddWm) {
            mWindowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
            mParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT < 19) {
                mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                mParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                    WindowManager.LayoutParams.FLAG_FULLSCREEN;
            mParams.format = PixelFormat.RGBA_8888;
            mParams.gravity = Gravity.LEFT | Gravity.TOP;
            mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            mParams.windowAnimations = android.R.style.Animation_Translucent;
            setParams(mParams);
            mWindowManager.addView(this, mParams);
            isAddWm = true;
        }
    }


    protected abstract void setParams(WindowManager.LayoutParams params);

    @Override
    public void removeFloat() {
        try {
            if (mWindowManager != null && isAddWm) {
                mWindowManager.removeView(this);
            }
            isAddWm = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initDataBeforView() {
    }

    //更改浮窗的位置
    protected void updateViewLayout() {
        if (mWindowManager != null && mParams != null && isAddWm) {
            mWindowManager.updateViewLayout(this, mParams);
        }

    }
}

