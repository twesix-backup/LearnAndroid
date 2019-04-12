package com.example.twesix.learn.android.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.cases.BroadcastExample;
import com.example.twesix.learn.android.cases.NotificationExample;
import com.example.twesix.learn.android.cases.ServiceExample;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, BroadcastExample.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
