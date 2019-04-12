package com.example.twesix.learn.android.cases;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;
import com.example.twesix.learn.android.common.MyApplication;

public class NotificationExample extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_example);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationExample.class);
        int NOTIFICATION_ID = 111;

        findViewById(R.id.button_add_notification).setOnClickListener((View v) ->
        {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Warning")
                    .setContentText("you have an urgent message to handle...")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setChannelId(MyApplication.MY_CHANNEL_ID)
                    .build();
            notificationManager.notify(NOTIFICATION_ID, notification);
        });

        findViewById(R.id.button_remove_notification).setOnClickListener((View v) ->
        {
            notificationManager.cancel(NOTIFICATION_ID);
        });
    }
}
