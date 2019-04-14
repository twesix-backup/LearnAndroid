package com.example.twesix.learn.android.cases;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.twesix.learn.android.R;
import com.example.twesix.learn.android.activity.BaseActivity;
import com.example.twesix.learn.android.common.MyApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyLocation extends BaseActivity
{
    TextView location_info;
    public AMapLocationClient aMapLocationClient = new AMapLocationClient(MyApplication.getContext());
    public AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
    public AMapLocationListener aMapLocationListener = (AMapLocation aMapLocation) ->
    {
        if (aMapLocation == null)
        {
            log("failed to locate your position, aMapLocation is null");
        }
        else
        {
            if (aMapLocation.getErrorCode() == 0)
            {
                showAllLocationInformation(aMapLocation);
            }
            else
            {
                log
                (
                        "AMapError, location Error," +
                        " ErrCode:"
                        + aMapLocation.getErrorCode() +
                        ", errInfo:"
                        + aMapLocation.getErrorInfo()
                );
            }
        }
    };

    void startLocation()
    {
        log("start location");
        stopLocation();
        aMapLocationClient.setLocationListener(aMapLocationListener);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        if (checkPermission())
        {
            aMapLocationClient.startLocation();
        }
        else
        {
            log("开始授权申请");
        }
    }

    void stopLocation()
    {
        log("stop location");
        aMapLocationClient.startLocation();
    }

    void showAllLocationInformation(AMapLocation aMapLocation)
    {
        log("showing location info...");
        //获取定位时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date(aMapLocation.getTime());

        String info = "定位时间:" + df.format(date) + "\n"
         + "当前定位结果来源:" + aMapLocation.getLocationType() + "\n"
         + "纬度: " + aMapLocation.getLatitude() + "\n"
         + "经度: " + aMapLocation.getLongitude() + "\n"
         + "精度信息: " + aMapLocation.getAccuracy() + "\n"
         + "地址: " + aMapLocation.getAddress() + "\n"
         + "国家信息: " + aMapLocation.getCountry() + "\n"
         + "省信息: " + aMapLocation.getProvince() + "\n"
         + "城市信息: " + aMapLocation.getCity() + "\n"
         + "城区信息: " + aMapLocation.getDistrict() + "\n"
         + "街道信息: " + aMapLocation.getStreet() + "\n"
         + "街道门牌号信息 " + aMapLocation.getStreetNum() + "\n"
         + "城市编码: " + aMapLocation.getCityCode() + "\n"
         + "地区编码: " + aMapLocation.getAdCode() + "\n"
         + "当前定位点的AOI信息: " + aMapLocation.getAoiName() + "\n"
         + "当前室内定位的建筑物Id: " + aMapLocation.getBuildingId() + "\n"
         + "当前室内定位的楼层: " + aMapLocation.getFloor() + "\n"
         + "GPS的当前状态: " + aMapLocation.getGpsAccuracyStatus() + "\n";
        location_info.setText(info);
    }

    final int PERMISSION_REQUEST_CODE = 1;
    List<String> permissionList = new ArrayList<>();

    boolean checkPermission()
    {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty())
        {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
            return false;
        }
        else
        {
            return true;
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
                    startLocation();
                }
                else
                {
                    showToast("发生未知授权错误");
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        location_info = findViewById(R.id.location_info);
        findViewById(R.id.button_single).setOnClickListener((View v) ->
        {
            // 出行模式
            aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
            // 运动模式
            aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
            // 签到模式
            aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);

            aMapLocationClientOption.setOnceLocation(true);
            aMapLocationClientOption.setOnceLocationLatest(true); // 最近3s最精准的一次定位
            startLocation();
        });
        findViewById(R.id.button_multi).setOnClickListener((View v) ->
        {
            aMapLocationClientOption.setInterval(1000);
            aMapLocationClientOption.setNeedAddress(true);
            startLocation();
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        aMapLocationClient.onDestroy();
    }
}
