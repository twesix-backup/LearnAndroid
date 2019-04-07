package com.example.twesix.learn.android;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HandleClickByInterface extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_click_by_interface);
        findViewById(R.id.button_interface_1).setOnClickListener(this);
        findViewById(R.id.button_interface_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_interface_1:
            {
                Toast.makeText(this, "button interface 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button_interface_2:
            {
                Toast.makeText(this, "button interface 2", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
