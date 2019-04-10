package com.example.twesix.learn.android.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.twesix.learn.android.R;

public class LifeCycle extends AppCompatActivity {

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LifeCycle", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        if (savedInstanceState != null)
        {
            Log.d("saved data", savedInstanceState.getString("key"));
        }

        Button normal = findViewById(R.id.button_normal_activity);
        Button dialog = findViewById(R.id.button_dialog_activity);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycle.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifeCycle.this, DialogStyle.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d("LifeCycle", "on destroy");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("LifeCycle", "on restart");
        super.onRestart();
    }

//  完整生命期: onCreate, onDesTroy, onRestart
//  ---------------------


    @Override
    protected void onStart() {
        Log.d("LifeCycle", "on start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LifeCycle", "on stop");
        super.onStop();
    }

//  可见生命期: onStart, onStop
//  ---------------------

    @Override
    protected void onPause() {
        Log.d("LifeCycle", "on pause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("LifeCycle", "on resume");
        super.onResume();
    }
//  前台生命期: onCPause, onResume
//  ---------------------

}
