package com.base.widget.inf;

import android.view.View;

/***
 * 加载状态
 */
public interface ILoadState {

	/**
	 * 加载开始
	 */
	int MESSAGE_ID_COMMON_LOADDATA_START = 0;
	/**
	 * 加载为空
	 */
	int MESSAGE_ID_COMMON_LOADDATA_EMPTY = 1;
	/**
	 * 加载失败
	 */
	int MESSAGE_ID_COMMON_LOADDATA_FAILED = 2;
	/**
	 * 加载结束
	 */
	int MESSAGE_ID_COMMON_LOADDATA_FINISH = 3;
	/**
	 * 无网络
	 */
	int MESSAGE_ID_COMMON_LOADDATA_NONET = 4;

	/**
	 * 获取加载中视图
	 *
	 * @return
	 */
	 View getLoadingView();

	/**
	 * 获取加载失败视图
	 *
	 * @return
	 */
	 View getLoadFailedView();

	/***
	 * 获取加载数据为空视图
	 *
	 * @return
	 */
	 View getEmptyView();

	/***
	 * 获取无网络视图
	 *
	 * @return
	 */
	 View getNonetView();

	/***
	 * 获取内容视图
	 *
	 * @return
	 */
	 View getContentView();

	/***
	 * 初始化视图
	 */
	 void initStateViews();

	/**
	 * 开始加载
	 */
	 void onLoadStart();

	/**
	 * 加载失败
	 */
	 void onLoadFailed();

	/**
	 * 加载为空
	 */
	 void onLoadEmpty();

	/**
	 * 加载结束
	 */
	 void onLoadSuccess();
	/***
	 * 无网络
	 *
	 * @return
	 */
	 void onLoadNonet();
}
