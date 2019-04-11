package com.example.twesix.learn.android.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.twesix.learn.android.common.ActivityCollector;
import com.example.twesix.learn.android.common.MyApplication;
import com.example.twesix.learn.android.common.MyJSON;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseActivity extends AppCompatActivity
{
    public MyOkHttp myOkHttp;

    class MySharedPreferences
    {
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        void putString(String key, String value)
        {
            editor.putString(key, value);
            editor.apply();
        }
        void putInt(String key, int value)
        {
            editor.putInt(key, value);
            editor.apply();
        }
        void putBoolean(String key, boolean value)
        {
            editor.putBoolean(key, value);
            editor.apply();
        }
        String getString(String key)
        {
            return sharedPreferences.getString(key, null);
        }
        int getInt(String key)
        {
            return sharedPreferences.getInt(key, -1);
        }
        boolean getBoolean(String key)
        {
            return sharedPreferences.getBoolean(key, false);
        }
    }

    public class MyOkHttp
    {

        public void getString(String url, Callback callback)
        {
            String result = null;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        public void postForm(String url, HashMap<String, String> form, Callback callback)
        {
            FormBody.Builder body = new FormBody.Builder();
            body.add("x-requested-by", "OkHttp");
            for (String key : form.keySet())
            {
                body.add(key, form.get(key));
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body.build())
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        public void postString(String url, String bodyString, Callback callback)
        {
            MediaType Text = MediaType.parse("text/plain; charset=utf-8");
            RequestBody body = RequestBody.create(Text, bodyString);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        public void postStringAsJson(String url, String json, Callback callback)
        {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
    }


//  完整生命期: onCreate, onDestroy, onRestart
//  可见生命期: onStart, onStop
//  前台生命期: onCPause, onResume

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
        Log.d("BaseActivity", getClass().getSimpleName() + " on save instance state");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d("BaseActivity", getClass().getSimpleName() + " on create");
        if (savedInstanceState != null)
        {
            Log.d("BaseActivity", "saved data: key=" + savedInstanceState.getString("key"));
        }
        myOkHttp = new MyOkHttp();
    }

    @Override
    protected void onRestart() {
        Log.d("BaseActivity", getClass().getSimpleName() + " on restart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("BaseActivity", getClass().getSimpleName() + " on destroy");
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onStart() {
        Log.d("BaseActivity", getClass().getSimpleName() + " on start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("LifeCycle", "on stop");
        super.onStop();
    }

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

    void finishAllActivity()
    {
        ActivityCollector.finishAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 1:
            {
                if (resultCode == RESULT_OK)
                {
                    String url = data.getStringExtra("url");
                    Log.d("MyWebView result:", url);
                }
                break;
            }
        }
    }

    public final void showToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public final void showProgressDialog(String title, String message, boolean cancelable)
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public final void showAlertDialog(String title, String message, boolean cancelable, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.setPositiveButton("ok", positive);
        dialog.setNegativeButton("cancel", negative);
        dialog.show();
    }
}
