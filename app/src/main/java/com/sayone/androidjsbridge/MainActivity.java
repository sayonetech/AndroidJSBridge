package com.sayone.androidjsbridge;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity MyActivity = this;
        webView= (WebView) findViewById(R.id.webview);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                Window.PROGRESS_VISIBILITY_ON);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                MyActivity.setTitle("Loading...");
                MyActivity.setProgress(progress * 100);

                if (progress == 100)
                    MyActivity.setTitle("Android JS Bridge");
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new WebAppInterface(this), "JB");


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/jsbridge.html");

    }

    public class WebAppInterface {

        Context context;

        WebAppInterface(Context c) {
            context= c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        }

    }

}
