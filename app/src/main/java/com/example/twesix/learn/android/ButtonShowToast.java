package com.example.twesix.learn.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonShowToast extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_show_toast);

        Button button_show_toast = (Button) findViewById(R.id.button_show_toast);
        button_show_toast.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ButtonShowToast.this, "you clicked the button", Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }
}
