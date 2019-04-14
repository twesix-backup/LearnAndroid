package com.example.twesix.learn.android.cases;

import android.os.Bundle;
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
import java.util.Date;

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
        aMapLocationClient.setLocationListener(aMapLocationListener);
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        aMapLocationClient.startLocation();
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
