package com.base.widget.inf;

public interface IInitView {

    /**
     * initDataBeforView()
     *
     * 属性
     * @return void    返回类型
     * 异常
     * @Title: initDataBeforView
     * 描述 页面初始化前获得数据
     */
     void initDataBeforView();

    /**
     * initView()
     *
     * 属性
     * @return void    返回类型
     * 异常
     * @Title: initView
     * 描述 初始化布局
     */
     void initView();

    /**
     * initData()
     *
     * 属性
     * @return void    返回类型
     * 异常
     * @Title: initData
     * 描述 初始化数据
     */
     void initData();

    /**
     * initListener
     *
     * 属性
     * @return void
     * 异常
     * 描述 初始化事件
     */
     void initListener();
}
