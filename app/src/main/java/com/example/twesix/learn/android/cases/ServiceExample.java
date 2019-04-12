package com.example.twesix.learn.android.cases;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;
import com.example.twesix.learn.android.service.BaseIntentService;
import com.example.twesix.learn.android.service.BaseService;
import com.example.twesix.learn.android.service.DaemonService;
import com.example.twesix.learn.android.service.ForegroundService;

public class ServiceExample extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_example);

        findViewById(R.id.start).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, BaseIntentService.class);
            startService(intent);
        });
        findViewById(R.id.stop).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, BaseIntentService.class);
            stopService(intent);
        });

        ServiceConnection serviceConnection = new ServiceConnection()
        {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service)
            {
                log("on service connected");
                BaseService.MyBinder binder = (BaseService.MyBinder) service;
                binder.sendMessage(name.toString() + ": this is a message");
            }

            @Override
            public void onServiceDisconnected(ComponentName name)
            {
                log(name.toString());
                log("on service disconnected");
            }
        };
        findViewById(R.id.bind).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, ForegroundService.class);
            bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        });
        findViewById(R.id.unbind).setOnClickListener((View v) ->
        {
            // 针对一个service connection只能调用一次, 否则会崩溃
            try
            {
                unbindService(serviceConnection);
            }
            catch
            (Exception e)
            {
                e.printStackTrace();
            }
        });
    }
}
