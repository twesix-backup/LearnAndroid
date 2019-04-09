package com.example.twesix.learn.android;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebView extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                showProgressDialog("证书错误", "该网站的证书无法验证,请小心访问", true);
                handler.proceed();
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Intent result = new Intent();
        result.putExtra("url", url);
        setResult(RESULT_OK, intent);
        finish();
    }
}
