package com.example.twesix.learn.android.cases;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;
import com.example.twesix.learn.android.receiver.LocalBroadcastReceiver;
import com.example.twesix.learn.android.receiver.NetworkObserver;

public class BroadcastExample extends BaseActivity
{

    IntentFilter intentFilter;
    NetworkObserver networkObserver;
    LocalBroadcastManager localBroadcastManager;
    LocalBroadcastReceiver localBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_example);

        intentFilter = new IntentFilter();
        networkObserver = new NetworkObserver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        // register a broadcast dynamically
//        registerReceiver(networkObserver, intentFilter);

        Button button_send_broadcast = findViewById(R.id.button_send_broadcast);
        button_send_broadcast.setEnabled(false);
        button_send_broadcast.setEnabled(true);
        button_send_broadcast.setOnClickListener((View v) ->
        {
            Intent intent = new Intent("intent.EXAMPLE_BROADCAST");
            intent.putExtra("data", "some data");
            intent.setComponent(new ComponentName("com.example.twesix.learn.android", "com.example.twesix.learn.android.receiver.GlobalBroadcastReceiver"));
            sendBroadcast(intent);
            log("send broadcast: intent.EXAMPLE_BROADCAST");
        });
        findViewById(R.id.button_local_broadcast).setOnClickListener((View v) ->
        {
            localBroadcastManager = LocalBroadcastManager.getInstance(this);
            localBroadcastReceiver = new LocalBroadcastReceiver();

            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("intent.LOCAL_BROADCAST");
            
            localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);

            Intent intent = new Intent("intent.LOCAL_BROADCAST");
            intent.putExtra("data", "some data");
            intent.setComponent(new ComponentName("com.example.twesix.learn.android", "com.example.twesix.learn.android.receiver.LocalBroadcastReceiver"));
            localBroadcastManager.sendBroadcast(intent);
            log("send broadcast: intent.LOCAL_BROADCAST");
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
//        unregisterReceiver(networkObserver);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

}
