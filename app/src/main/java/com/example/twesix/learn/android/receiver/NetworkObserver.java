package com.example.twesix.learn.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.twesix.learn.android.common.MyApplication;

public class NetworkObserver extends BaseBroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);

        log("network changed");
        NetworkInfo networkInfo = MyApplication.connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null)
        {
            if (networkInfo.isAvailable())
            {
                log("network available");
                log(networkInfo.getExtraInfo()); // epc.tmobile.com
                log(networkInfo.getSubtypeName()); // LTE
            }
            else
            {
                log("network not available");
                log(networkInfo.getExtraInfo());
                log(networkInfo.getSubtypeName());
            }
        }
        else
        {
            log("no network info");
        }
    }
}
