package com.example.twesix.learn.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class WebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.d("WebView", url);
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Intent result = new Intent();
        result.putExtra("url", url);
        setResult(RESULT_OK, intent);
        finish();
    }
}
