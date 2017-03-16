package com.veyron.www.money.activitys;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.veyron.www.money.R;

import dmax.dialog.SpotsDialog;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView back_img;
    WebView mWebView;
    private String url;
    // 用于记录出错页面的url 方便重新加载
    private String mFailingUrl = null;
    private SpotsDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        back_img = (ImageView)findViewById(R.id.back_img);
        back_img.setOnClickListener(this);
        url = getIntent().getExtras().getString("url");
        mDialog = new SpotsDialog(this,"loading....");
        mDialog.show();
        mWebView = (WebView)findViewById(R.id.webview);
        initWebViewSettings();
        initWebview();
    }

    private void initWebViewSettings() {
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                            v.requestFocusFromTouch();
                        }
                        break;
                }
                return false;
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        // mWebView.getSettings().setDomStorageEnabled(true);
        // mWebView.getSettings().setBlockNetworkImage(false);//防止阻塞加载图片
         mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        if (Build.VERSION.SDK_INT < 19) {
            if (Build.VERSION.SDK_INT >8) {
                mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
            }
        }
        if (Build.VERSION.SDK_INT >= 19){
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
    }
    private void initWebview() {
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(url);
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 在webview加载下一页，而不会打开浏览器
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(mDialog !=null && mDialog.isShowing())
                mDialog.dismiss( );
        }
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e("TAG","onReceivedError"+description);
            mFailingUrl = failingUrl;//记录失败的url

            //view.setLayoutParams();
            view.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
            view.loadUrl("file:///android_asset/error.html");//添加显示本地文件
        }


    }
    class AndroidAndJSInterface{
        @JavascriptInterface
        public void reLoad(){
            // mWebView.loadUrl(url);
            Log.e("TAG", "reload.....");
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    //重新加载
                    if (mFailingUrl != null){
                        Log.e("TAG","在reload...");
                        mWebView.loadUrl(mFailingUrl);
                    }
                }
            });
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
            mWebView.resumeTimers();
        }
    }
    @Override
    public void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
            mWebView.pauseTimers();
        }
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.clearCache(true);
        mWebView.destroy();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
