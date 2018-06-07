package com.base.widget.base.dialog;

import android.content.Context;
import android.os.Bundle;

import com.base.widget.base.dialog.inf.IResetLayout;


/**
 * Created by zengyingzhi on 2016/4/13.
 */
public abstract class BaseSizeDialog extends BaseDialog implements IResetLayout {

    public BaseSizeDialog(Context context) {
        super(context);
    }

    public BaseSizeDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setDialogSize();
    }
    /**
     * 窗口属性
     */
    @Override
    public void initWindow() {
      /*  Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0f;// 背景明暗度(0.0f~~1.0f越大越暗)
        window.setAttributes(lp);*/
    }

}
