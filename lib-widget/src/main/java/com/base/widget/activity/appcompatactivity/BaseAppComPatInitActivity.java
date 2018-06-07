package com.base.widget.activity.appcompatactivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.base.widget.R;
import com.base.widget.activity.inf.ICompatView;

/**
 * Created by linbaosheng on 2015/10/15.
 */
public abstract class BaseAppComPatInitActivity extends BaseCompatActivity implements ICompatView {
    //头
    protected Toolbar mToolbar;
    //身体
    protected FrameLayout mBodyLayout;
    protected void initActionBar() {
        setSupportActionBar(mToolbar);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBodyLayout = (FrameLayout) findViewById(R.id.framelayout_fragment);
        mBodyLayout.addView(initView());
        initDataAfterView();
        initActionBar();
    }
}










