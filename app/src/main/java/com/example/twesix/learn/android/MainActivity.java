package com.example.twesix.learn.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyWebView.class);
        intent.putExtra("url", "https://diary.twesix.cn");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
