package com.kolarbear.wanandroid.di.module;

import com.kolarbear.wanandroid.api.ApiService;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/15.
 */

@Module
public class ActivityModule {

    IView iView;
    public ActivityModule(IView iView) {
        this.iView = iView;
    }
    @Provides
    @ActivityScope
    IView provideView(){
        return iView;
    }
}
