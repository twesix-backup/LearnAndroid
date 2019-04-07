package com.example.twesix.learn.android;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class TextAndImageWidgets extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    TextView textView;
    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_and_image_widgets);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.button_set_image).setOnClickListener(this);
        findViewById(R.id.button_set_text).setOnClickListener(this);
        findViewById(R.id.button_set_progress).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_set_text:
            {
                textView.setText(editText.getText());
                break;
            }
            case R.id.button_set_image:
            {
                imageView.setImageResource(R.drawable.img02);
                break;
            }
            case R.id.button_set_progress:
            {
                if (progressBar.getVisibility() == View.GONE)
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                }
                int progress = progressBar.getProgress();
                progress = progress + 10;
                progressBar.setProgress(progress);
                break;
            }
            default: break;
        }
    }
}
