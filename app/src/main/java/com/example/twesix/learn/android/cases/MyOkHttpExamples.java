package com.example.twesix.learn.android.cases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyOkHttpExamples extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ok_http_examples);

        TextView text_view_get_string = findViewById(R.id.text_view_get_string);
        TextView text_view_post_string = findViewById(R.id.text_view_post_string);
        TextView text_view_post_form = findViewById(R.id.text_view_post_form);
        TextView text_view_post_string_as_json = findViewById(R.id.text_view_post_string_as_json);

        myOkHttp.getString("http://httpbin.org/uuid", new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(() ->
                {
                    showToast(e.getMessage());
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(() ->
                {
                    try
                    {
                        text_view_get_string.setText(response.body().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });

        myOkHttp.postString("http://httpbin.org/anything", "this is the post body plain string, or a json string", new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(() ->
                {
                    showToast(e.getMessage());
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(() ->
                {
                    try
                    {
                        text_view_post_string.setText(response.body().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });

        myOkHttp.postStringAsJson("http://httpbin.org/anything", "{}", new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(() ->
                {
                    showToast(e.getMessage());
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(() ->
                {
                    try
                    {
                        text_view_post_string_as_json.setText(response.body().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });

        HashMap<String, String> formBody = new HashMap<>();
        formBody.put("key1", "value1");
        formBody.put("key2", "value2");
        formBody.put("key3", "value3");
        myOkHttp.postForm("http://httpbin.org/anything", formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                runOnUiThread(() ->
                {
                    showToast(e.getMessage());
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response)
            {
                runOnUiThread(() ->
                {
                    try
                    {
                        text_view_post_form.setText(response.body().string());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        });
    }
}
