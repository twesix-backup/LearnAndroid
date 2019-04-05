package com.example.twesix.learn.android;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
                Toast.makeText(Menu.this, "you clicked the button", Toast.LENGTH_SHORT)
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
            case R.id.menu_main_activity:
            {
//                Intent intent = new Intent(".intent.ACTION_START");
//                intent.addCategory("android.intent.category.DEFAULT");
                Intent intent = new Intent(Menu.this, ButtonShowToast.class);
                startActivity(intent);
                break;
            }
            case R.id.menu_web_view:
            {
                Intent intent = new Intent("intent.WEB_VIEW");
                intent.setData(Uri.parse("https://www.twesix.cn"));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode)
        {
            case 1:
            {
                if (resultCode == RESULT_OK)
                {
                    String url = data.getStringExtra("url");
                    Log.d("Menu", url);
                }
                break;
            }
        }
    }
}
