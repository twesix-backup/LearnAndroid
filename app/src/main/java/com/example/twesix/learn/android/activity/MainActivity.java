package com.example.twesix.learn.android.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.cases.BroadcastExample;
import com.example.twesix.learn.android.cases.CameraAlbum;
import com.example.twesix.learn.android.cases.Chat;
import com.example.twesix.learn.android.cases.LoginActivity;
import com.example.twesix.learn.android.cases.MyListView;
import com.example.twesix.learn.android.cases.MyLocation;
import com.example.twesix.learn.android.cases.MyRecyclerView;
import com.example.twesix.learn.android.cases.MyWebView;
import com.example.twesix.learn.android.cases.NotificationExample;
import com.example.twesix.learn.android.cases.ServiceExample;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_base_activity).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, BaseActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.button_common_widgets).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, CommonWidgets.class);
            startActivity(intent);
        });
        findViewById(R.id.button_fragment_static).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, FragmentStatic.class);
            startActivity(intent);
        });
        findViewById(R.id.button_runtime_permission).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, RuntimePermission.class);
            startActivity(intent);
        });
        findViewById(R.id.button_broadcast_example).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, BroadcastExample.class);
            startActivity(intent);
        });
        findViewById(R.id.button_camera_album).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, CameraAlbum.class);
            startActivity(intent);
        });
        findViewById(R.id.button_chat).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, Chat.class);
            startActivity(intent);
        });
        findViewById(R.id.button_login_activity).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.button_my_list_view).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, MyListView.class);
            startActivity(intent);
        });
        findViewById(R.id.button_my_location).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, MyLocation.class);
            startActivity(intent);
        });
        findViewById(R.id.button_my_ok_http_example).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, MyOkHttp.class);
            startActivity(intent);
        });
        findViewById(R.id.button_my_recycler_view).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, MyRecyclerView.class);
            startActivity(intent);
        });
        findViewById(R.id.button_my_web_view).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, MyWebView.class);
            startActivity(intent);
        });
        findViewById(R.id.button_notification_example).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, NotificationExample.class);
            startActivity(intent);
        });
        findViewById(R.id.button_service_example).setOnClickListener((View v) ->
        {
            Intent intent = new Intent(this, ServiceExample.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
