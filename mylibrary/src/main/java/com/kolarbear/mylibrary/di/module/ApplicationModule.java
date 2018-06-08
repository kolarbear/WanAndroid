package com.kolarbear.mylibrary.di.module;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/15.
 */
@Module
public class ApplicationModule {
    Application app;

    public ApplicationModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApp()
    {
        return app;
    }
}
