package com.base.widget.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.base.util.ScreenUtil;
import com.base.widget.base.dialog.inf.IFloat;
import com.base.widget.inf.IInitView;

/**
 * Created by linbinghuang
 * Date 2015/11/15
 */
public abstract class BaseDialog extends Dialog implements IFloat, IInitView {
    public BaseDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        init();

    }

    public void init() {
//        getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBeforView();
        initView();
        initListener();
        initData();
        DialogManager.getDialogsManager().addDialog(this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        DialogManager.getDialogsManager().getDialogStack().remove(this);
    }

    @Override
    public void addFloat() {
        if (!isShowing()) {
            show();
        }
    }

    @Override
    public void removeFloat() {
        if (isShowing()) {
            dismiss();
        }
    }

    @Override
    public void initDataBeforView() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        getWindow().setGravity(Gravity.CENTER);
        lp.width = ScreenUtil.getCurrentScreenWidth1(getContext());
        getWindow().setAttributes(lp);
    }

}
