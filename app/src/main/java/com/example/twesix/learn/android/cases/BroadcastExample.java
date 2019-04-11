package com.example.twesix.learn.android.cases;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;
import com.example.twesix.learn.android.receiver.NetworkObserver;

public class BroadcastExample extends BaseActivity
{

    IntentFilter intentFilter;
    NetworkObserver networkObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_example);

        Button button1 = findViewById(R.id.button_interface_1);
        button1.setEnabled(false);
        button1.setEnabled(true);
        button1.setOnClickListener((View v) ->
        {
            Intent intent = new Intent("intent.EXAMPLE_BROADCAST");
            sendBroadcast(intent);
        });
        findViewById(R.id.button_interface_2).setOnClickListener((View v) ->
        {
            showToast("button interface 2");
        });

        // register a broadcast dynamically
        intentFilter = new IntentFilter();
        networkObserver = new NetworkObserver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkObserver, intentFilter);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(networkObserver);
    }

}
