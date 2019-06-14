package com.example.twesix.learn.android.common;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyOKHTTP
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
