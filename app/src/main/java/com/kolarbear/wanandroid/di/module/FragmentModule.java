package com.kolarbear.wanandroid.di.module;

import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/5/15.
 */
@Module
public class FragmentModule {

    IView view;

    public FragmentModule(IView view) {
        this.view = view;
    }


    @Provides
    @FragmentScope
    IView provideView()
    {
        return view;
    }
}
