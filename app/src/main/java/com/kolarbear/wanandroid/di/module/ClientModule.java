package com.kolarbear.wanandroid.di.module;


import android.app.Application;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.http.HttpGlobalHandler;
import com.kolarbear.wanandroid.http.RequestInterceptor;
import com.kolarbear.wanandroid.http.ResultJsonDeser;
import com.kolarbear.wanandroid.utils.SpUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *  建造者模式
 *  Created by Administrator on 2018/5/15.
 */
@Module
public class ClientModule {

    private static final int TIME_OUT = 60;

    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10*1024*1024;//缓存文件最大值为10Mb

    private Interceptor[] interceptors;

    private String baseUrl;

    private HttpGlobalHandler handler;

    private Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(BaseBean.class,new ResultJsonDeser())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    private ClientModule(Builder builder)
    {
        this.baseUrl = builder.baseUrl;
        this.handler = builder.handler;
        this.interceptors = builder.interceptors;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client)
    {
        return configRetrofit(new Retrofit.Builder(),client);
    }

    /**
     * 配置Retrofit
     * @param builder
     * @param client
     * @return
     */
    private Retrofit configRetrofit(Retrofit.Builder builder,OkHttpClient client)
    {
       return builder.baseUrl(baseUrl)
               .client(client)
               .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rxjava2
               .addConverterFactory(GsonConverterFactory.create(gson)) //gson
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
        return configOkhttpClient(new OkHttpClient.Builder(),cache,interceptor);
    }

    /**
     * 配置OkHttpClient
     * @param builder
     * @param cache
     * @param interceptor
     * @return
     */
    private OkHttpClient configOkhttpClient(OkHttpClient.Builder builder,Cache cache,Interceptor interceptor)
    {
        OkHttpClient.Builder client = builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS).cache(cache)
                .addInterceptor(interceptor)
                ;
        if (interceptors!=null&&interceptors.length>0)
        {
            for (Interceptor i :interceptors) {
                client.addInterceptor(i);
            }
        }
        return client.build();
    }


    @Singleton
    @Provides
    Interceptor provideInteceptor(){
        return new RequestInterceptor(handler);
    }

    @Singleton
    @Provides
    Cache provideCache(File file){
        return new Cache(file,HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
    }

    @Singleton
    @Provides
    File provideFile(App application){
        return SpUtil.getCacheFile(application);
    }

   public static class Builder{
        private Interceptor[] interceptors;
        private String baseUrl;
        private HttpGlobalHandler handler;
        public Builder baseUrl(String baseUrl)
        {
            if (TextUtils.isEmpty(baseUrl))
            {
                throw new IllegalArgumentException("baseUrl can not be empty!");
            }
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder globalHandler(HttpGlobalHandler httpGlobalHandler)
        {
            if (httpGlobalHandler==null)
            {
                throw new NullPointerException("HttpGlobalHandler can not be null");
            }
            this.handler = httpGlobalHandler;
            return this;
        }

        public Builder inteceptors(Interceptor[] interceptors){
            this.interceptors = interceptors;
            return this;
        }

        public ClientModule build()
        {
            if (TextUtils.isEmpty(baseUrl))
            {
                throw new IllegalArgumentException("baseUrl can not be empty!");
            }
            return new ClientModule(this);
        }
    }

}
