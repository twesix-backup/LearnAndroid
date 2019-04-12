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

        // register a broadcast dynamically
        intentFilter = new IntentFilter();
        networkObserver = new NetworkObserver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkObserver, intentFilter);

        Button button_send_broadcast = findViewById(R.id.button_send_broadcast);
        button_send_broadcast.setEnabled(false);
        button_send_broadcast.setEnabled(true);
        button_send_broadcast.setOnClickListener((View v) ->
        {
            Intent intent = new Intent("intent.EXAMPLE_BROADCAST");
            sendBroadcast(intent);
        });
        findViewById(R.id.button_interface_2).setOnClickListener((View v) ->
        {
            showToast("button interface 2");
        });

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(networkObserver);
    }

}
