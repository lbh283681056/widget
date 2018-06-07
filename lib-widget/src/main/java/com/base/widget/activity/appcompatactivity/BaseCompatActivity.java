package com.base.widget.activity.appcompatactivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.base.widget.activity.manager.ActivitysManager;
import com.base.widget.activity.manager.HandlerManager;
import com.base.widget.activity.manager.IHandlerHelp;


/**
 * Created by linbinghuang on 2016/10/26.
 */
public class BaseCompatActivity extends AppCompatActivity implements IHandlerHelp {
    /**
     * Handler管理器
     */
    private HandlerManager mHandlerManager;
    /**
     * Activity管理器
     */
    private ActivitysManager mActivitysManager;

    /**
     * 设置状态栏背景状态
     */
    private void setTranslucentStatus() {
        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public BaseCompatActivity() {
        init();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTranslucentStatus();
        mActivitysManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeAllCallbacksAndMessages();
        mActivitysManager.finishActivity(this);

    }

    public void init() {
        mHandlerManager = new HandlerManager();
        mActivitysManager = mActivitysManager.getActivitysManager();
    }
    @Override
    public void addHandler(Handler handler) {
        mHandlerManager.addHandler(handler);
    }

    @Override
    public void removeHandler(Handler handler) {
        mHandlerManager.removeHandler(handler);
    }

    @Override
    public void removeAllCallbacksAndMessages() {
        mHandlerManager.removeAllCallbacksAndMessages();
    }
}
