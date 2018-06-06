package com.kolarbear.wanandroid.aspectj;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.aspectj.annotation.LoginCheck;
import com.kolarbear.wanandroid.utils.UserController;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 在收藏和取消收藏前进行检测用户是否登录
 * Created by Administrator on 2018/6/6.
 */
@Aspect
public class LoginAspectJ {

    private static final String TAG = "LoginAspectJ";

    @Pointcut("execution(@com.kolarbear.wanandroid.aspectj.annotation.LoginCheck * *(..))")
    public void pointcut()
    {

    }

    @Around("pointcut()")
    public void loginCheck(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LoginCheck annotation = method.getAnnotation(LoginCheck.class);
        int type = annotation.type();
        Log.e(TAG, "loginCheck: "+point+type);
        if (UserController.getInstance().isLogin())
        {
            point.proceed();
        }else {
            ToastUtils.showShort("请先登录");
        }
    }


}
