package com.example.twesix.learn.android.cases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;

public class Camera extends BaseActivity
{

    public static final int TAKE_PHOTO = 0;
    public  Uri imageUri;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.image_view);

        findViewById(R.id.button_shot_image).setOnClickListener((View v) ->
        {
            File snapshot = new File(getExternalCacheDir(), "snapshot.jpg");
            try
            {
                if (snapshot.exists())
                {
                    snapshot.delete();
                }
                snapshot.createNewFile();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (Build.VERSION.SDK_INT >= 24)
            {
                imageUri = FileProvider.getUriForFile(Camera.this, "com.example.twesix.learn.android", snapshot);
            }
            else
            {
                imageUri = Uri.fromFile(snapshot);
            }

            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case TAKE_PHOTO:
            {
                if (resultCode == RESULT_OK)
                {
                    try
                    {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageView.setImageBitmap(bitmap);
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
