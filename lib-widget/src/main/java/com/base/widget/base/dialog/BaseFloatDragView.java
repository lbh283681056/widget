package com.base.widget.base.dialog;

import android.content.Context;
import android.content.res.Configuration;
import android.view.MotionEvent;

import com.base.util.ScreenUtil;
import com.base.widget.base.dialog.inf.IFloatDrag;


/**
 * Created by linbinghuang
 * Date 2015/10/22
 * 可以拖拽的浮窗
 */
public abstract class BaseFloatDragView extends BaseFloat implements IFloatDrag {

    private float mRawX;
    private float mStartX;
    private float mRawY;
    private float mStartY;
    private float mStatusBarHeight;
    private float mX;
    private float mY;
    //是否在拖拽状态
    protected boolean isMoveing;
    //是否能拖拽
    protected boolean isMove = true;


    public BaseFloatDragView(Context context) {
        super(context);
    }

    /*
       * 由于父级控件拖动优先级比较高
       */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (isMove) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mStartY = ev.getY();
                    mStartX = ev.getX();
                    mRawX = getXInScreen(ev);
                    mRawY = getYInScreen(getContext(), ev);
                    actionDown(ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mRawX = getXInScreen(ev);
                    mRawY = getYInScreen(getContext(), ev);
                    if (isMoveing || (Math.abs(mRawX - mX) > 25 || Math.abs(mRawY - mY) > 25)) {
                        updateWindowPosition();
                        actionMove(ev);
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    isMoveing = false;
                    mRawX = getXInScreen(ev);
                    mRawY = getYInScreen(getContext(), ev);
                    actionUp(ev);
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isMove) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mX = getXInScreen(ev);
                    mY = getYInScreen(getContext(), ev);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (Math.abs(mRawX - mX) > 25 || Math.abs(mRawY - mY) > 25) {
                        isMoveing = true;
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isMoveing) return true;
                default:

                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }


    /**
     * 相对屏幕Y的位置
     */
    private float getYInScreen(Context context, MotionEvent event) {
        return getYInScreen(context, event.getRawY());
    }

    /**
     * 相对屏幕Y的位置
     */
    protected float getYInScreen(Context context, float y) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return y;
        } else {
            if (mStatusBarHeight == 0) {
                mStatusBarHeight = ScreenUtil.getStatusHeight(context);
            }
            return y - mStatusBarHeight;
        }
    }

    /**
     * 相对屏幕X的位置
     */
    private float getXInScreen(MotionEvent event) {
        return event.getRawX();
    }

    /**
     * 更新窗口参数，控制浮动窗口移动
     */
    public void updateWindowPosition() {
        try {
            mParams.x = (int) (mRawX - mStartX);
            mParams.y = (int) (mRawY - mStartY);
            updateViewLayout();
        } catch (Exception e) {
        }
    }

}

