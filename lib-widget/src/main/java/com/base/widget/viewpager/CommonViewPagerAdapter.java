package com.base.widget.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * 作者 Comsys-linbinghuang
 * 类名: CommonViewPagerAdapter
 * 描述 viewpager适配器
 * 时间 2014-7-28 下午3:35:21
 */

public class CommonViewPagerAdapter extends PagerAdapter {

    public void setmViews(List<? extends View> mViews) {

        this.mViews = mViews;
    }

    public List<? extends View> getmViews() {
        return mViews;
    }

    private List<? extends View> mViews;

    public CommonViewPagerAdapter(List<? extends View> views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);// 删除页卡
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
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return PagerAdapter.POSITION_NONE;
    }

}
