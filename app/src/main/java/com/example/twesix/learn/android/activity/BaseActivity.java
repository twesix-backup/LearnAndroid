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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d("BaseActivity", getClass().getSimpleName() + " activated");
        new MyOkHttp().get("http://httpbin.org/uuid", new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("request failed");
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            showProgressDialog("request response", response.body().string(), true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
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

    class PersistenceBySharedPreferences
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

}
