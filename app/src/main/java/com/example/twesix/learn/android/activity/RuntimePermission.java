package com.example.twesix.learn.android.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.twesix.learn.android.R;

import java.util.ArrayList;
import java.util.List;

public class RuntimePermission extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);

        checkPermission();
    }

    final int PERMISSION_REQUEST_CODE = 1;
    List<String> permissionList = new ArrayList<>();

    void checkPermission()
    {

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.READ_CALENDAR);
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.CAMERA);
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.READ_CONTACTS);
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.RECORD_AUDIO);
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.BODY_SENSORS);
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
//        {
//            permissionList.add(Manifest.permission.READ_SMS);
//        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty())
        {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        }
        else
        {
            log("所需权限已全部被授予, 无需申请");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE:
            {
                if (grantResults.length > 0)
                {
                    for (int i = 0; i < permissions.length; i ++)
                    {
                        log(permissions[i] + ": " + Integer.toString(grantResults[i]));
                    }
                    for (int grantResult : grantResults)
                    {
                        if (grantResult != PackageManager.PERMISSION_GRANTED)
                        {
                            showToast("必须同意所有权限才能使用本程序");
                            finish();
                            return;
                        }
                    }
                    showToast("授权成功");
                }
                else
                {
                    showToast("发生未知授权错误");
                }
                finish();
            }
        }
    }
}
