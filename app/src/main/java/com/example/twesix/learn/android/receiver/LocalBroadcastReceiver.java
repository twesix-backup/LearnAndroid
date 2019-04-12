package com.example.twesix.learn.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocalBroadcastReceiver extends BaseBroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        log(intent.getStringExtra("data"));
        Toast.makeText(context, "broadcast received", Toast.LENGTH_SHORT).show();
    }
}
