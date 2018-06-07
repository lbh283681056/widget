package com.base.widget.help;

import com.base.widget.adapter.IAdapterHelp;
import com.base.widget.inf.ILoadState;
import com.base.widget.inf.ILoadViewState;
import com.base.widget.inf.IloadViewResult;

import java.util.List;

/**
 * 作者 linbinghuang
 * 记载后逻辑处理
 */
public class LoadViewResultHelper {

//	/**
//	 * 加載空或加載成功后的布局
//	 * 属性 data 本次加载的数据
//	 * 属性 isLastPage 是否是最后一页
//	 * 属性 adapterHelp 适配器帮助接口
//	 * 属性 viewResult
//	 * 属性 iLoadState
//	 */
//	public static void loadIsEmpty(List data, int isLastPage,
//								   IAdapterHelp adapterHelp, ILoadViewState viewResult,
//								   ILoadState iLoadState) {
//		if (data == null||data.isEmpty()) {
//			if (adapterHelp == null || adapterHelp.getCount() == 0) {
//				iLoadState.onLoadEmpty();
//			} else {
//				viewResult.onLoadEmpty();
//			}
//		} else {
//			viewResult.onLoadSuccess();
//			if (isLastPage==1) {
//				viewResult.onLoadComplete();
//			}
//		}
//	}
	/**
	 * 加載空或加載成功后的布局
	 * 属性 data 本次加载的数据
	 * 属性 isLastPage 是否是最后一页
	 * 属性 adapterHelp 适配器帮助接口
	 * 属性 viewResult
	 * 属性 iLoadState
	 */
	public static void loadIsEmpty(List data, int isLastPage,
								   IAdapterHelp adapterHelp, ILoadViewState viewResult,
								   IloadViewResult iLoadState) {
		if (data == null || data.isEmpty()) {
			if (adapterHelp == null || adapterHelp.getCount() == 0) {
				iLoadState.onLoadEmpty();
			} else {
				viewResult.onLoadEmpty();
			}
		} else {
			iLoadState.onLoadSuccess();
			viewResult.onLoadSuccess();
			if (isLastPage==1) {
				viewResult.onLoadComplete();
			}
		}
	}

	/**
	 * 加载失败后布局
	 * 属性 adapterHelp
	 * 属性 viewResult
	 * 属性 iLoadState
	 */
	public static void loadIsFailed(IAdapterHelp adapterHelp,
									ILoadViewState viewResult, IloadViewResult iLoadState) {
		if (adapterHelp == null || adapterHelp.getCount() == 0) {
			iLoadState.onLoadFailed();
		} else {
			viewResult.onLoadFailed();
		}
	}
	/**
	 * 加载失败后布局
	 * 属性 adapterHelp
	 * 属性 viewResult
	 * 属性 iLoadState
	 */
	public static void loadIsFailed(IAdapterHelp adapterHelp,
									ILoadViewState viewResult, ILoadState iLoadState) {
		if (adapterHelp == null || adapterHelp.getCount() == 0) {
			iLoadState.onLoadFailed();
		} else {
			viewResult.onLoadFailed();
		}
	}

}
