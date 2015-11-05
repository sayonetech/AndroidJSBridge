package com.sayone.androidjsbridge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class ActivityWebViewInteraction extends AppCompatActivity {
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
        webView.loadUrl("https://cvs-s.shopcurbside.com/");
        webView.setWebViewClient(new CustomWebViewClient());

    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {

            handler.proceed("curbside", "curbside");

        }
    }

}
