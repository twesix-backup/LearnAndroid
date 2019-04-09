package com.example.twesix.learn.android;

import android.app.Activity;
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

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseActivity extends AppCompatActivity  implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
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
    public void onClick(View v)
    {
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

    final void showToast(String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    final void showProgressDialog(String title, String message, boolean cancelable)
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    final void showAlertDialog(String title, String message, boolean cancelable, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative)
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.setPositiveButton("ok", positive);
        dialog.setNegativeButton("cancel", negative);
        dialog.show();
    }

    class PersistenceByFile
    {
        void replace(String fileName, String content)
        {
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            try
            {
                fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (bufferedWriter != null)
                    {
                        bufferedWriter.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        void append(String fileName, String content)
        {
            FileOutputStream fileOutputStream = null;
            BufferedWriter bufferedWriter = null;
            try
            {
                fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                bufferedWriter.write(content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (bufferedWriter != null)
                    {
                        bufferedWriter.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        String read(String fileName)
        {
            FileInputStream in = null;
            BufferedReader reader = null;
            StringBuilder content = new StringBuilder();
            try
            {
                in = openFileInput(fileName);
                reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    content.append(line);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    if (reader != null)
                    {
                        reader.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return content.toString();
        }
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

    class MyOkHttp
    {
        void get(String url, Callback callback)
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }

        String getStringSync(String url)
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            try
            {
                Response response = call.execute();
                if (response.body() != null)
                {
                    return response.body().string();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return "";
        }
        Object getJsonSync(String url)
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            try
            {
                Response response = call.execute();
                if (response.body() != null)
                {
                    return new MyJSON().parse(response.body().string());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        void post(String url, FormBody.Builder body, Callback callback)
        {
            body.add("x-requested-by", "OkHttp");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body.build())
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
        }
        Object postJsonStringSync(String url, String json)
        {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, json);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            try
            {
                Response response = call.execute();
                if (response.body() != null)
                {

                    return new MyJSON().parse(response.body().string());
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
    public class MyJSON
    {
        Object parse(String json)
        {
            return JSON.parseObject(json);
        }
        String serialize(Object object)
        {
            return JSON.toJSONString(object);
        }
    }
}
