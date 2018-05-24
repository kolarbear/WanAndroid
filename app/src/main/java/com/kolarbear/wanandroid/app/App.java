package com.kolarbear.wanandroid.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.kolarbear.wanandroid.BuildConfig;
import com.kolarbear.wanandroid.constant.Constant;
import com.kolarbear.wanandroid.di.component.AppComponent;
import com.kolarbear.wanandroid.di.component.DaggerAppComponent;
import com.kolarbear.wanandroid.di.module.ApplicationModule;
import com.kolarbear.wanandroid.di.module.ClientModule;
import com.kolarbear.wanandroid.di.module.ServiceModule;
import com.kolarbear.wanandroid.http.HttpGlobalHandler;
import com.squareup.leakcanary.LeakCanary;

import me.yokeyword.fragmentation.Fragmentation;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 *  本项目由
 *  mvp
 *  +dagger2
 *  +retrofit
 *  +rxjava
 *  +butterknife
 *  +rxlifecycle组成
 * Created by Administrator on 2018/5/15.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);//内存泄漏检测
        //初始化工具类
        Utils.init(this);
        initARouter();
        if (BuildConfig.DEBUG)
        {
            Timber.plant(new Timber.DebugTree());
        }
        initAppComponent();
        initFragmentation();
    }

    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                .install();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .clientModule(getClientModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    public AppComponent appComponent()
    {
        return appComponent;
    }

    private ClientModule getClientModule(){
        return ClientModule.builder()
                .baseUrl(Constant.BASE_URL)
                .globalHandler(new HttpGlobalHandler() {
                    @Override
                    public Request beforeRequest(Interceptor.Chain chain, Request request) {
                        return request;
                    }

                    @Override
                    public Response beforeResponse(String result, Interceptor.Chain chain, Response response) {
                        return response;
                    }
                })
                .inteceptors(getInteceptors())
                .build();
    }

    private Interceptor[] getInteceptors()
    {
        return null;
    }

}
