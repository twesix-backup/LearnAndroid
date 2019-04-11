package com.example.twesix.learn.android.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.MainActivity;
import com.example.twesix.learn.android.cases.ServiceExample;

public class ForegroundService extends BaseService
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        String my_chanel_id = "com.example.twesix.learn.android";
        String my_chanel_name = "twesix chanel";
        NotificationChannel notificationChannel = null;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            notificationChannel = new NotificationChannel(my_chanel_id, my_chanel_name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent = new Intent(this, ServiceExample.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Warning")
                .setContentText("a foreground service is running...")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setChannelId(my_chanel_id)
                .build();
        startForeground(1111, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return super.onStartCommand(intent, flags, startId);
    }
}
