package com.example.twesix.learn.android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HandleClick extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_click);
        ClickHandler clickHandler = new ClickHandler(this);
        findViewById(R.id.button_interface_1).setOnClickListener(clickHandler);
        findViewById(R.id.button_interface_2).setOnClickListener(clickHandler);
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

class ClickHandler implements View.OnClickListener
{
    private Activity activity;
    ClickHandler(Activity activity)
    {
        this.activity = activity;
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button_interface_1:
            {
                Toast.makeText(this.activity, "button interface 1", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.button_interface_2:
            {
                Toast.makeText(this.activity, "button interface 2", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
