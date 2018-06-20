package com.kolarbear.wanandroid.aspectj;

import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 在每次点击跳转详情页前都去检查网络状态
 * Created by Administrator on 2018/6/13.
 */
@Aspect
public class NetAspectJ {

    private static final String TAG = "NetAspectJ";
    @Pointcut("execution(* com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener.onItemClick(..))" +
            "||execution(* com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener.onItemChildClick(..))" +
            "||execution(* com.kolarbear.wanandroid.ui.knowledge.RightAdapter.OnItemClickListener.click(..))")
    public void pointcut()
    {

    }

    @Around("pointcut()")
    public void NetCheck(ProceedingJoinPoint point) throws Throwable {
        Log.e(TAG, "NetCheck: "+point.toString());
        if (NetworkUtils.isConnected())
        {
            point.proceed();
        }else {
            ToastUtils.showShort("请先检查你的网络连接");
        }
    }
}
