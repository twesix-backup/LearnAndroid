package com.example.twesix.learn.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.twesix.learn.android.service.DaemonService;

public class BootCompleteReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent daemonService = new Intent(context, DaemonService.class);
        context.startService(intent);
    }
}
