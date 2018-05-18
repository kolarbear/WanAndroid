package com.kolarbear.wanandroid.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  网络请求全局处理
 *  Created by Administrator on 2018/5/18.
 */

public interface HttpGlobalHandler {

    Request beforeRequest(Interceptor.Chain chain, Request request);

    Response beforeResponse(String result, Interceptor.Chain chain, Response response);

}
