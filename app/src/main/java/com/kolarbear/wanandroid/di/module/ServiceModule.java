package com.kolarbear.wanandroid.di.module;

import com.kolarbear.wanandroid.api.ApiService;

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
    ApiService provideService(Retrofit retrofit)
    {
        return retrofit.create(ApiService.class);
    }
}
