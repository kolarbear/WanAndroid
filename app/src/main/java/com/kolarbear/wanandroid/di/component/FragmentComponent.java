package com.kolarbear.wanandroid.di.component;

import com.kolarbear.wanandroid.di.module.FragmentModule;
import com.kolarbear.wanandroid.di.scope.FragmentScope;
import com.kolarbear.wanandroid.ui.home.HomeFragment;
import com.kolarbear.wanandroid.ui.knowledge.KnowledgeFragment;
import com.kolarbear.wanandroid.ui.main.MainFragment;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/15.
 */
@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(HomeFragment fragment);

    void inject(KnowledgeFragment fragment);

}
