package com.kolarbear.wanandroid.di.module;

import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.di.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/15.
 */
@Module
public class ApplicationModule {
    App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp()
    {
        return app;
    }
}
