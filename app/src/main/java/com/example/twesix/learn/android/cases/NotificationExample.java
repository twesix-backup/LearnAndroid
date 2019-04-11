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

public class NotificationExample extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_example);

        findViewById(R.id.button).setOnClickListener((View v) ->
        {
            String my_chanel_id = "com.example.twesix.learn.android";
            String my_chanel_name = "twesix chanel";
            NotificationChannel notificationChannel = null;
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                notificationChannel = new NotificationChannel(my_chanel_id, my_chanel_name, NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            Intent intent = new Intent(this, NotificationExample.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification1 = new NotificationCompat.Builder(this, my_chanel_id)
                    .setContentTitle("Warning")
                    .setContentText("you have an urgent message to handle...")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .build();
            notificationManager.notify(111, notification1);
        });
    }
}
