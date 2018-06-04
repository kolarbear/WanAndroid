package com.kolarbear.wanandroid.ui.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kolarbear.wanandroid.utils.UserController;

/**
 *  登录拦截器
 *  Created by Administrator on 2018/6/4.
 */
@Interceptor(priority = 666, name = "登录拦截器")
public class LoginInterceptor implements IInterceptor{
    private static final String TAG = "LoginInterceptor";
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (UserController.getInstance().isLogin())
        {
            callback.onContinue(postcard);
        }else {

          if ((postcard.getPath().contains("/login/LoginActivity")))
            {
                callback.onContinue(postcard);
            }else {
            callback.onInterrupt(new RuntimeException("先登录吧哥们"));
            ARouter.getInstance().build("/login/LoginActivity")
                    .navigation();
          }
        }
    }

    @Override
    public void init(Context context) {
        Log.e(TAG, "init: "+"The LoginInterceptor has init.");
    }
}
