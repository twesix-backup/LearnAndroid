package com.example.twesix.learn.android.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;

public class HandleClick extends BaseActivity {

    IntentFilter intentFilter;
    NetworkObserver networkObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_click);

        ClickHandler clickHandler = new ClickHandler(this);
        Button button1 = findViewById(R.id.button_interface_1);
        button1.setEnabled(false);
        button1.setEnabled(true);
        button1.setOnClickListener(clickHandler);
        findViewById(R.id.button_interface_2).setOnClickListener(clickHandler);

        intentFilter = new IntentFilter();
        networkObserver = new NetworkObserver();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkObserver, intentFilter);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(networkObserver);
    }

    class NetworkObserver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable())
            {
                showToast("network available");
            }
            else
            {
                showToast("network unavailable");
            }
        }
    }

    class ClickHandler implements View.OnClickListener
    {
        private Activity activity;
        ClickHandler(Activity activity)
        {
            this.activity = activity;
        }
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.button_interface_1:
                {
                    Intent intent = new Intent("intent.EXAMPLE_BROADCAST");
                    sendBroadcast(intent);
                    break;
                }
                case R.id.button_interface_2:
                {
                    showToast("button interface 2");
                    break;
                }
            }
        }
    }
}
