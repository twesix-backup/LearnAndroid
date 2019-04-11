package com.example.twesix.learn.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class BaseService extends Service
{

    public class MyBinder extends Binder
    {
        public void sendMessage(String message)
        {
            log("send message: " + message);
        }
        public String getStatus(String statusName)
        {
            log("get status: " + statusName);
            return null;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        log("on start command");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        log("on bind");
        return new MyBinder();
    }

    @Override
    public void onCreate()
    {
        log("on create");
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        log("on destroy");
        super.onDestroy();
    }

    public final void log(String log)
    {
        Log.d("[[[" + getClass().getSimpleName() + "]]] ", log);
    }
}
