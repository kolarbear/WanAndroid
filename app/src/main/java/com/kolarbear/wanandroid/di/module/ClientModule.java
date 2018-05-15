package com.kolarbear.wanandroid.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *  建造者模式
 *  Created by Administrator on 2018/5/15.
 */
@Module
public class ClientModule {


    @Provides
    Retrofit provideRetrofit()
    {
        return new Retrofit.Builder().baseUrl("").build();
    }


    static class Builder{

    }

}
