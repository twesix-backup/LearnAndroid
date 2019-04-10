package com.example.twesix.learn.android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.fragment.MyWebView;

public class FragmentStatic extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_static);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyWebView myWebView = (MyWebView) fragmentManager.findFragmentById(R.id.fragment_webview);
        myWebView.goToUrl("https://diary.twesix.cn");
    }
}
