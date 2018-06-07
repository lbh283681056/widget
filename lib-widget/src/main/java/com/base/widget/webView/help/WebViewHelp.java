package com.base.widget.webView.help;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by linbinghuang on 2016/8/28.
 */
public class WebViewHelp {
    public static void setSetting(Context context, WebView mWebView) {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setBlockNetworkImage(false); //可以加载网络图片
        webSettings.setAllowFileAccess(true); // 允许访问文件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(false);
//        webSettings.setLoadWithOverviewMode(true);//可以实现双击放大，再次双击显示缩略图的功能。
        //缩放
        webSettings.setBuiltInZoomControls(false); // 设置是否显示缩放按钮
//        webSettings.setUseWideViewPort(true); // 设置此属性，可任意比例缩放, 回到初始缩放等级, 自适应屏幕
        webSettings.setSupportZoom(false); // 支持缩放
//        mWebView.setInitialScale(70);//最小缩放等级

//        if(Build.VERSION.SDK_INT >= 19) {
//            webSettings.setLoadsImagesAutomatically(true);
//        } else {
//            webSettings.setLoadsImagesAutomatically(false);
//        }

        //缓存系列
        webSettings.setAppCacheEnabled(true); // 设置启动缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 设置缓存模式
        webSettings.setAppCacheMaxSize(1024 * 10);// 设置最大缓存
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true); // 开启 database storage API 功能

//        String cacheDirPath = context.getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
//		String cacheDirPath = BaseApplication.getInstance().getCachePath();;
//        webSettings.setDatabasePath(cacheDirPath); // 设置数据库缓存路径
//        webSettings.setAppCachePath(cacheDirPath); // 设置 Application Caches 缓存目录


//ScrollBar样式设定
    }
}
