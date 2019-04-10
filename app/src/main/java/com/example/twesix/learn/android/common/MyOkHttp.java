package com.example.twesix.learn.android.common;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttp
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

                return MyJSON.parse(response.body().string());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
