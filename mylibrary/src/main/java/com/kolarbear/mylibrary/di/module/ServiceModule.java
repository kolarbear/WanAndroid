package com.kolarbear.mylibrary.di.module;


import com.kolarbear.mylibrary.IService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 全局提供Service
 * Created by Administrator on 2018/5/15.
 */
@Module
public class ServiceModule {

    @Provides
    @Singleton
    IService provideService(Retrofit retrofit)
    {
        return retrofit.create(IService.class);
    }
}
