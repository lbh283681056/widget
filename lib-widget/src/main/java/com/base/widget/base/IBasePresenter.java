package com.base.widget.base;

/**
 * Created by linbinghuang on 2016/12/1.
 */
public interface IBasePresenter {

    //观察主要做一些eventbus 或者一些初始化
    void subscribe();
    //结束观察
    void unsubscribe();

}
