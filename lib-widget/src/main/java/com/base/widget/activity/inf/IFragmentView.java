package com.base.widget.activity.inf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IFragmentView {
    void initDataBeforView();
    View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
     void initDataAfterView();
    void initListener();
    void initActionbar();

}
