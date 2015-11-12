package com.sayone.androidjsbridge;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by renjithraj on 11/11/15.
 */
public class BridgeWebView extends WebView {

    public BridgeWebView(Context context) {
        super(context);
        init(context);
    }

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        this.setWebViewClient(new WebViewClient());
        this.addJavascriptInterface(new CSWebAppInterface(context), "jb");
        this.getSettings().setJavaScriptEnabled(true);
        this.loadUrl("file:///android_asset/jsbridge.html");
    }

    public class CSWebAppInterface {

        Context context;

        CSWebAppInterface(Context c) {
            context= c;
        }

        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        }

    }
}
