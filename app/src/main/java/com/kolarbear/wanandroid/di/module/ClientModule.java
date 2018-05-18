package com.kolarbear.wanandroid.di.module;


import android.text.TextUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *  建造者模式
 *  Created by Administrator on 2018/5/15.
 */
@Module
public class ClientModule {

    private String baseUrl;

    private ClientModule(Builder builder)
    {
        this.baseUrl = builder.baseUrl;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client)
    {
        return new Retrofit.Builder().baseUrl(baseUrl)
                .client(client)
                .build();
    }

    public static Builder builder()
    {
        return new Builder();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(Cache cache, Interceptor interceptor)
    {
        return new OkHttpClient();
    }


    static class Builder{

        private String baseUrl;

        public Builder baseUrl(String baseUrl)
        {
            if (TextUtils.isEmpty(baseUrl))
            {
                throw new IllegalArgumentException("baseUrl can not be empty!");
            }
            this.baseUrl = baseUrl;
            return this;
        }

        public ClientModule build()
        {
            return new ClientModule(this);
        }
    }

}
