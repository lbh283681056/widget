package com.base.widget.activity.manager;

import android.os.Handler;

public interface IHandlerHelp {

	
	void addHandler(Handler handler);
	
	void removeHandler(Handler handler);
	
	 void removeAllCallbacksAndMessages();
}
