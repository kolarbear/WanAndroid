package com.kolarbear.wanandroid.di.component;

import android.app.Application;

import com.kolarbear.wanandroid.api.ApiService;
import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.di.module.ApplicationModule;
import com.kolarbear.wanandroid.di.module.ClientModule;
import com.kolarbear.wanandroid.di.module.ServiceModule;
import com.kolarbear.wanandroid.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AppComponent 提供全局依赖
 * Created by Administrator on 2018/5/15.
 */
@Singleton
@Component(modules = {ClientModule.class, ServiceModule.class, ApplicationModule.class})
public interface AppComponent {

    ApiService service();

    App app();
}
