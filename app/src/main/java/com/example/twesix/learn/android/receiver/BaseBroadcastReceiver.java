package com.example.twesix.learn.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BaseBroadcastReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        log("on receive");
    }

    public final void log(String log)
    {
        Log.d("[[[" + getClass().getSimpleName() + "]]] ", log);
    }
}
