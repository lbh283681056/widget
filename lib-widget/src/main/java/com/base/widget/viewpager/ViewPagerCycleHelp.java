package com.base.widget.viewpager;

import android.os.Handler;
import android.support.v4.view.ViewPager;

/**
 * Created by linbinghuang on 2017/3/9.
 */

public class ViewPagerCycleHelp {

    public ViewPager.OnPageChangeListener getCycleOnPageChangeListener(final ViewPager viewPager, final int size, final ViewPager.OnPageChangeListener listener){
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(null != listener){
                    listener.onPageScrolled(position, positionOffset, positionOffsetPixels) ;
                }
            }

            @Override
            public void onPageSelected(int position) {
                //首位扩展的item
//延迟执行才能看到动画
                if(position == 0) new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(size - 2, false);//跳转到末位
                    }
                }, 50);
                else if(position ==size-1){//末位扩展的item
                    //延迟执行才能看到动画
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(1,false) ;//跳转到首位
                        }
                    }, 50) ;

                }

                if(null != listener){
                    listener.onPageSelected(position) ;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(null != listener){
                    listener.onPageScrollStateChanged(state) ;
                }
            }
        };
    }
}
