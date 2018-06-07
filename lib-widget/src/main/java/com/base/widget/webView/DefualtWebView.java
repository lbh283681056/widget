package com.base.widget.webView;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.base.util.NetworkUtils;
import com.base.widget.help.LoadViewHelper;
import com.base.widget.inf.IloadViewResult;
import com.base.widget.webView.help.WebViewHelp;
import com.base.widget.webView.ui.BaseWebView;

/**
 * Created by linbinghuang on 2016/8/28.
 * 主要是封装一些webview页面的显示
 * 加载页面超时页面错误页面等等的显示
 */
public abstract class   DefualtWebView extends BaseWebView implements IloadViewResult {
    private LoadViewHelper mLoadViewHelp;
    private WebViewClient mWebViewClient;
    private boolean isLoadError;
    private Handler mNonErrorHandler;
    private static final int NonErrorWhat = 1;
    private static final int NonErrorTime = 30000;
    private String mErrorUrl = "";


    public static long time ;
    public DefualtWebView(Context context) {
        super(context);
    }

    public DefualtWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DefualtWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLoadStart() {
        mLoadViewHelp.onLoadStart();
    }

    @Override
    public void onLoadFailed() {
        mLoadViewHelp.onLoadFailed();
    }

    @Override
    public void onLoadEmpty() {
        mLoadViewHelp.onLoadEmpty();
    }

    @Override
    public void onLoadSuccess() {
        mLoadViewHelp.onLoadSuccess();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        WebViewHelp.setSetting(getContext(), this);
        setHorizontalScrollBarEnabled(false);//水平不显示
        setVerticalScrollBarEnabled(false); //垂直不显示
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//可使滚动条不占位
        setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                DefualtWebView.time = System.currentTimeMillis();
            }
        });
    }


    @Override
    public void initListener() {
        setWebViewClient(null);
        setDownloadListener(new MyWebViewDownLoadListener());
    }

    @Override
    public void setWebViewClient(WebViewClient client) {

        mWebViewClient = client;
        super.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (mWebViewClient != null) {
                    mWebViewClient.onPageStarted(view, url, favicon);
                }
                isLoadError = false;
                removeHandler();
                if (mNonErrorHandler != null && !url.equals(getNonErrorUrl())) {

                    mNonErrorHandler.sendEmptyMessageDelayed(NonErrorWhat, NonErrorTime);
                }
                DefualtWebView.time = System.currentTimeMillis();
                onLoadStart();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!getSettings().getLoadsImagesAutomatically()) {
                    getSettings().setLoadsImagesAutomatically(true);
                }
                if (mWebViewClient != null) {
                    mWebViewClient.onPageFinished(view, url);
                }
                if (!isLoadError) {
                    onLoadSuccess();
                }
                removeHandler();
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if (mWebViewClient != null) {
                    mWebViewClient.onReceivedError(view, errorCode, description, failingUrl);
                }
                if (NetworkUtils.isNetworkAvailable(getContext())) {
                    onLoadEmpty();
                } else {
                    onLoadFailed();

                }
                removeHandler();
                isLoadError = true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (mWebViewClient != null) {
//                    mWebViewClient.shouldOverrideUrlLoading(view, url);
//                    view.loadUrl(url);
//                    Log.e("webview", "shouldOverrideUrlLoading");
//                }
//                return false;

                if(mWebViewClient!=null){
                    mWebViewClient.shouldOverrideUrlLoading(view, url);
                }
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    getContext().startActivity(intent);
                } catch (Exception e) {
                }
                return true;
            }

//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//                if (mWebViewClient != null) {
//                    return  mWebViewClient.shouldInterceptRequest(view, request);
//                }
//                return super.shouldInterceptRequest(view, request);
//            }

            @Override
            public void onLoadResource(WebView view, String url) {
                if (mWebViewClient != null) {
                    mWebViewClient.onLoadResource(view, url);
                }
                super.onLoadResource(view, url);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                super.onFormResubmission(view, dontResend, resend);
                if (mWebViewClient != null) {
                    mWebViewClient.onFormResubmission(view, dontResend, resend);
                }
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                if (mWebViewClient != null) {
                    mWebViewClient.doUpdateVisitedHistory(view, url, isReload);
                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();

                if (mWebViewClient != null) {
                    mWebViewClient.onReceivedSslError(view, handler, error);
                }
            }

//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
//                super.onReceivedClientCertRequest(view, request);
//                if (mWebViewClient != null) {
//                    mWebViewClient.onReceivedClientCertRequest(view, request);
//                }
//            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
                if (mWebViewClient != null) {
                    mWebViewClient.onReceivedHttpAuthRequest(view, handler, host, realm);
                }
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                if (mWebViewClient != null) {
                    return mWebViewClient.shouldOverrideKeyEvent(view, event);
                }
                return super.shouldOverrideKeyEvent(view, event);
            }

//            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//            @Override
//            public void onUnhandledInputEvent(WebView view, InputEvent event) {
//                super.onUnhandledInputEvent(view, event);
//                if (mWebViewClient != null) {
//                    mWebViewClient.onUnhandledInputEvent(view, event);
//                }
//            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
                if (mWebViewClient != null) {
                    mWebViewClient.onScaleChanged(view, oldScale, newScale);
                }
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
                if (mWebViewClient != null) {
                    mWebViewClient.onReceivedLoginRequest(view, realm, account, args);
                }
            }
        });
    }

    //这里写的是默认加载布局 如果要更改的话可以自行重写
    public abstract LoadViewHelper.ILoadHelper getLocalLoadHelper();

    //必须先进性初始化
    public void init(String url, String errorUrl, LoadViewHelper.ILoadHelper iLoadHelper) {
        mErrorUrl = errorUrl;
        LoadViewHelper.ILoadHelper mILoadHelper = iLoadHelper;
        if (mILoadHelper == null) {
            mILoadHelper = getLocalLoadHelper();
        }
        mLoadViewHelp = new LoadViewHelper(mILoadHelper);
        loadUrl(url);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!getNonErrorUrl().equals("")) {
            mNonErrorHandler = getNonErrorHandler();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeHandler();
        mNonErrorHandler = null;
    }

    private void removeHandler() {
        if (mNonErrorHandler != null) {
            mNonErrorHandler.removeMessages(NonErrorWhat);
        }
    }


    //设置莫名错误的延迟发送页面
    public Handler getNonErrorHandler() {
        return new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                loadUrl(getNonErrorUrl());
            }
        };
    }

    //设置莫名错误地址
    public String getNonErrorUrl() {
        return mErrorUrl;
    }



    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype,

                                    long contentLength) {

            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            getContext().startActivity(intent);

        }

    }
//    @Override
//    public void setWebChromeClient(WebChromeClient client) {
//        super.setWebChromeClient(new WebChromeClient(){
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                Log.e("FFF","newProgress="+newProgress);
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                Log.e("FFF", "title=" + title);
//            }
//
//            @Override
//            public void onReceivedIcon(WebView view, Bitmap icon) {
//                super.onReceivedIcon(view, icon);
//                Log.e("FFF", "icon=" );
//            }
//
//            @Override
//            public void onShowCustomView(View view, CustomViewCallback callback) {
//                super.onShowCustomView(view, callback);
//                Log.e("FFF", "onReceivedIcon=");
//            }
//
//            @Override
//            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
//                super.onReceivedTouchIconUrl(view, url, precomposed);
//                Log.e("FFF", "onReceivedTouchIconUrl="+url);
//            }
//
//            @Override
//            public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
//                super.onShowCustomView(view, requestedOrientation, callback);
//                Log.e("FFF", "onShowCustomView=" + requestedOrientation);
//            }
//
//            @Override
//            public void onHideCustomView() {
//                super.onHideCustomView();
//                Log.e("FFF", "onHideCustomView=" );
//            }
//
//            @Override
//            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
//                Log.e("FFF", "onCreateWindow=");
//                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
//
//            }
//
//            @Override
//            public void onRequestFocus(WebView view) {
//                Log.e("FFF", "onRequestFocus=");
//                super.onRequestFocus(view);
//            }
//
//            @Override
//            public void onCloseWindow(WebView window) {
//                Log.e("FFF", "onCloseWindow=");
//                super.onCloseWindow(window);
//            }
//
//            @Override
//            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                Log.e("FFF", "onJsAlert=");
//                return super.onJsAlert(view, url, message, result);
//            }
//
//            @Override
//            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//                Log.e("FFF", "onJsConfirm=");
//                return super.onJsConfirm(view, url, message, result);
//            }
//
//            @Override
//            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//                Log.e("FFF", "onJsPrompt=");
//                return super.onJsPrompt(view, url, message, defaultValue, result);
//            }
//
//            @Override
//            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
//                Log.e("FFF", "onJsBeforeUnload=");
//                return super.onJsBeforeUnload(view, url, message, result);
//            }
//
//            @Override
//            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
//                super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
//                Log.e("FFF", "onExceededDatabaseQuota=");
//            }
//
//            @Override
//            public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
//                super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
//                Log.e("FFF", "onReachedMaxAppCacheSize=");
//            }
//
//            @Override
//            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
//                super.onGeolocationPermissionsShowPrompt(origin, callback);
//                Log.e("FFF", "onGeolocationPermissionsShowPrompt=");
//            }
//
//            @Override
//            public void onGeolocationPermissionsHidePrompt() {
//                super.onGeolocationPermissionsHidePrompt();
//                Log.e("FFF", "onGeolocationPermissionsHidePrompt=");
//            }
//
//            @Override
//            public void onPermissionRequest(PermissionRequest request) {
//                super.onPermissionRequest(request);
//                Log.e("FFF", "onPermissionRequest=");
//            }
//
//            @Override
//            public void onPermissionRequestCanceled(PermissionRequest request) {
//                super.onPermissionRequestCanceled(request);
//                Log.e("FFF", "onPermissionRequestCanceled=");
//            }
//
//            @Override
//            public boolean onJsTimeout() {
//                Log.e("FFF", "onJsTimeout=");
//                return super.onJsTimeout();
//
//            }
//
//            @Override
//            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
//                super.onConsoleMessage(message, lineNumber, sourceID);
//                Log.e("FFF", "onConsoleMessage="+lineNumber+"      "+sourceID);
//            }
//
//            @Override
//            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
//
//                return super.onConsoleMessage(consoleMessage);
//            }
//
//            @Override
//            public Bitmap getDefaultVideoPoster() {
//                return super.getDefaultVideoPoster();
//            }
//
//            @Override
//            public View getVideoLoadingProgressView() {
//                return super.getVideoLoadingProgressView();
//            }
//
//            @Override
//            public void getVisitedHistory(ValueCallback<String[]> callback) {
//                super.getVisitedHistory(callback);
//            }
//
//            @Override
//            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
//                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
//            }
//        });
//    }

}
