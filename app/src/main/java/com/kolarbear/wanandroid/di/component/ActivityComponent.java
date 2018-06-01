package com.kolarbear.wanandroid.di.component;

import com.kolarbear.wanandroid.ui.category_articles.ArticleListActivity;
import com.kolarbear.wanandroid.ui.collectlist.CollectListActivity;
import com.kolarbear.wanandroid.ui.login.LoginActivity;
import com.kolarbear.wanandroid.ui.main.MainActivity;
import com.kolarbear.wanandroid.di.module.ActivityModule;
import com.kolarbear.wanandroid.di.scope.ActivityScope;
import com.kolarbear.wanandroid.ui.search.SearchActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/5/15.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(ArticleListActivity activity);

    void inject(CollectListActivity activity);

    void inject(SearchActivity activity);
}
