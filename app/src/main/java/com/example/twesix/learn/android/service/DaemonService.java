package com.example.twesix.learn.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class DaemonService extends BaseService
{
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(() ->
        {
            // do something
        });
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int oneSecond = 1000;
        int oneMinute = 60 * oneSecond;
        int oneHour = 60 * oneMinute;
        long triggerAtTime = SystemClock.elapsedRealtime() + oneSecond;
        Intent intent1 = new Intent(this, DaemonService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent1, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
//        manager.setAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
//        manager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
//        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
