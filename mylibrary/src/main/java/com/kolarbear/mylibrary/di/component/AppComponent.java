package com.kolarbear.mylibrary.di.component;


import com.kolarbear.mylibrary.di.module.ApplicationModule;
import com.kolarbear.mylibrary.di.module.ClientModule;
import com.kolarbear.mylibrary.di.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AppComponent 提供全局依赖
 * Created by Administrator on 2018/5/15.
 */
@Singleton
@Component(modules = {ClientModule.class, ServiceModule.class, ApplicationModule.class})
public interface AppComponent {


}
