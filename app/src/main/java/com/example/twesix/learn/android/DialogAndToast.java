package com.example.twesix.learn.android;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DialogAndToast extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_and_toast);
        findViewById(R.id.button_toast).setOnClickListener(this);
        findViewById(R.id.button_alert_dialog).setOnClickListener(this);
        findViewById(R.id.button_proress_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_alert_dialog:
            {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("this is the title");
                dialog.setMessage("this is a message");
                dialog.setCancelable(false);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogAndToast.this, "positive clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogAndToast.this, "negative clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                break;
            }
            case R.id.button_toast:
            {
                Toast.makeText(this, "button interface 2", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button_proress_dialog:
            {
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("this is title");
                progressDialog.setMessage("this is message");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            }
        }
    }
}
