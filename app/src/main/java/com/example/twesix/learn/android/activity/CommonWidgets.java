package com.example.twesix.learn.android.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.cases.MyWebView;

public class CommonWidgets extends BaseActivity {

    EditText editText;
    TextView textView;
    ImageView imageView;
    ProgressBar progressBar;
    final Activity context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_widgets);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.button_toast).setOnClickListener((View v) ->
        {
            showToast("button interface 2");
        });
        findViewById(R.id.button_alert_dialog).setOnClickListener((View v) ->
        {
            DialogInterface.OnClickListener positive = (dialog, which) -> CommonWidgets.this.showToast("positive clicked");
            DialogInterface.OnClickListener negative = (dialog, which) -> showToast("negative clicked");
            showAlertDialog("this is the title", "this is a message", false, positive, negative);
        });
        findViewById(R.id.button_proress_dialog).setOnClickListener((View v) ->
        {
            showProgressDialog("this is title", "this is message", true);
        });
        findViewById(R.id.button_set_image).setOnClickListener((View v) ->
        {
            imageView.setImageResource(R.drawable.img02);
        });
        findViewById(R.id.button_set_text).setOnClickListener((View v) ->
        {
            textView.setText(editText.getText());
        });
        findViewById(R.id.button_set_progress).setOnClickListener((View v) ->
        {
            if (progressBar.getVisibility() == View.INVISIBLE)
            {
                progressBar.setVisibility(View.VISIBLE);
            }
            else
            {
                progressBar.setVisibility(View.INVISIBLE);
            }
            int progress = progressBar.getProgress();
            progress = progress + 10;
            progressBar.setProgress(progress);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_show_toast:
            {
                Toast.makeText(context, "you clicked the button", Toast.LENGTH_SHORT)
                        .show();
                break;
            }
            case R.id.menu_intent:
            {
//                finish();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.twesix.cn"));
                startActivity(intent);
                break;
            }
            case R.id.menu_web_view:
            {
                Intent intent = new Intent(this, MyWebView.class);
                intent.putExtra("url", "https://diary.twesix.cn");
                startActivityForResult(intent, 1);
                break;
            }
            case R.id.menu_dial:
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10010"));
                startActivity(intent);
                break;
            }
        }
        return true;
    }
}
