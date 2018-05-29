package com.kolarbear.wanandroid.utils;

import android.util.Log;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by Administrator on 2018/5/28.
 */

public class CookieManager implements CookieJar{
    private static final String TAG = "CookieManager";
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        for (Cookie c : cookies) {
            Log.e(TAG, "saveFromResponse: name>"+c.name()+"value>"+c.value());
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
