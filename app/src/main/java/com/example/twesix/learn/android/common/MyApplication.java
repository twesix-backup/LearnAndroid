package com.example.twesix.learn.android.common;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;

public class MyApplication extends Application
{
    private static Context context;
    public static String MY_CHANNEL_ID = "com.example.twesix.learn.android.notification";
    public static String MY_CHANNEL_NAME = "Learn Android";
    public static NotificationManager notificationManager;
    public static ConnectivityManager connectivityManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();

        NotificationChannel notificationChannel = null;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationChannel = new NotificationChannel(MY_CHANNEL_ID, MY_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static Context getContext()
    {
        return context;
    }
}
