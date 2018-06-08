package com.kolarbear.mylibrary.di.component;


import com.kolarbear.mylibrary.di.module.ActivityModule;
import com.kolarbear.mylibrary.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/15.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {


}
