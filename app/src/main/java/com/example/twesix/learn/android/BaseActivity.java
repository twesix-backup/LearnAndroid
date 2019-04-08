package com.example.twesix.learn.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity  implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v)
    {
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
}
