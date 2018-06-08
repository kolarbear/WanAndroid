package com.kolarbear.mylibrary.di.component;


import com.kolarbear.mylibrary.di.module.FragmentModule;
import com.kolarbear.mylibrary.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/15.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {


}
