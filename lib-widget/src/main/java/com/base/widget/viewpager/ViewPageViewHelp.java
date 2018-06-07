package com.base.widget.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by linbinghuang on 2016/7/31.
 * viewpage和tab通用
 */
public class ViewPageViewHelp implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mViewPager;
    private List<View> mTabView;
    private List<View> mViews;
    private IViewpageViewHelpCallBack mCallBack;

    public void setData(ViewPager viewPager, List<View> views, List<View> titles, int startIndex, IViewpageViewHelpCallBack callback) {
        mViewPager = viewPager;
        mTabView = titles;
        mViews = views;
        mCallBack = callback;
        mViewPager.setOnPageChangeListener(this);
        CommonViewPagerAdapter mAdapter = new CommonViewPagerAdapter(views);
        mViewPager.setAdapter(mAdapter);
        for (int i = 0; i < mTabView.size(); i++) {
            View v = mTabView.get(i);
            v.setTag(i);
            v.setOnClickListener(this);
        }
        mViewPager.setCurrentItem(startIndex);
        if (mCallBack != null) {
            if (!mViews.isEmpty()) {
                mCallBack.load(mViews.get(startIndex));
            }
            if (mTabView != null && mTabView.size() > 0){
                mCallBack.setSelected(mTabView.get(0),true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        mViewPager.setCurrentItem(index);
    }

    //改变底部的选中状态
    private void setTabs(int index) {
        int size = mTabView.size();
        if (size <= 1) return;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                if (mCallBack != null) {
                    mCallBack.setSelected(mTabView.get(i), true);
                    mCallBack.load(mViews.get(i));
                }

            } else {
                if (mCallBack != null) {
                    mCallBack.setSelected(mTabView.get(i), false);
                }
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setTabs(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public List<View> getViews() {
        return mViews;
    }

    public interface IViewpageViewHelpCallBack {
        void setSelected(View v, boolean isSelected);

        void load(View v);
    }
}
