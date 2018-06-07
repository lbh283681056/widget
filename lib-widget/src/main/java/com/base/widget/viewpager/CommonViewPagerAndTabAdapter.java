package com.base.widget.viewpager;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
  * 类名: CommonViewPagerAndTabAdapter
  * 描述 含tab适配器
  * 作者 Comsys-linbinghuang
  * 时间 2014-8-27 下午1:57:26
  */

public class CommonViewPagerAndTabAdapter extends PagerAdapter {

    private List<? extends View> mViews;
    private List<String> mTabs;

    public CommonViewPagerAndTabAdapter(List<? extends View> views, List<String> tabs) {
        this.mViews = views;
        this.mTabs = tabs;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));// 删除页卡
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position), 0);// 添加页卡
        return mViews.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;// 官方提示这样写
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTabs != null && mTabs.size() == mViews.size()) {
            return mTabs.get(position);
        }
        return super.getPageTitle(position);
    }

}
