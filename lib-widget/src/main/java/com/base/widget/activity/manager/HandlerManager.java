package com.base.widget.activity.manager;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler管理器，防止内存泄漏
 * 
 * 作者 LinXin
 * @Date 2014年7月29日 上午10:22:51
 */
public class HandlerManager implements IHandlerHelp {

	private List<Handler> mHandlerList;

	/**
	 * 添加一个Handler
	 * 
	 * 属性 handler
	 */
	public void addHandler(Handler handler) {
		if (mHandlerList == null)
			mHandlerList = new ArrayList<Handler>();
		mHandlerList.add(handler);
	}

	/**
	 * 移除一个Handler
	 * 
	 * 属性 handler
	 */
	public void removeHandler(Handler handler) {
		if (mHandlerList != null)
			mHandlerList.remove(handler);
	}

	/**
	 * 移除所有Handler以及其Callbacks和Messages
	 */
	public void removeAllCallbacksAndMessages() {
		if (mHandlerList != null) {
			for (int i = 0; i < mHandlerList.size(); i++) {
				Handler handler = mHandlerList.get(i);
				if (handler != null) {
					handler.removeCallbacksAndMessages(null);
					handler = null;
				}
			}
			mHandlerList.clear();
			mHandlerList = null;
		}
	}
}
