package com.base.widget.base.dialog.inf;

import android.view.MotionEvent;

/**
 * Created by linbinghuang
 * Date 2015/10/22
 * 拖拽接口
 */
public interface IFloatDrag {

//    public boolean isMoveFloat();
     void actionDown(MotionEvent ev);
     void actionMove(MotionEvent ev);
     void actionUp(MotionEvent ev);


}
