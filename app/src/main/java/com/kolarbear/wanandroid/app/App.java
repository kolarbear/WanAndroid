package com.kolarbear.wanandroid.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.kolarbear.wanandroid.di.component.AppComponent;
import com.kolarbear.wanandroid.di.component.DaggerAppComponent;
import com.kolarbear.wanandroid.di.module.ApplicationModule;
import com.kolarbear.wanandroid.di.module.ClientModule;
import com.kolarbear.wanandroid.di.module.ServiceModule;

/**
 * Created by Administrator on 2018/5/15.
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化工具类
        Utils.init(this);
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .clientModule(new ClientModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    public AppComponent appComponent()
    {
        return appComponent;
    }

}
