package com.example.twesix.learn.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BaseIntentService extends IntentService
{
    public BaseIntentService()
    {
        super("BaseIntentService");
        log("base intent service constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        log("on handle intent");
    }



    @Override
    public void onCreate()
    {
        super.onCreate();
        log("on create");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        log("on destroy");
    }

    public final void log(String log)
    {
        Log.d("[[[" + getClass().getSimpleName() + "]]] ", log);
    }
}
