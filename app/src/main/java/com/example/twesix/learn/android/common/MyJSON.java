package com.example.twesix.learn.android.common;

import com.alibaba.fastjson.JSON;

public class MyJSON
{
    public static Object parse(String json)
    {
        return JSON.parseObject(json);
    }
    public static String serialize(Object object)
    {
        return JSON.toJSONString(object);
    }
}
