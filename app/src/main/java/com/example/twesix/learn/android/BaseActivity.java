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

public class BaseActivity extends AppCompatActivity  implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
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
}
