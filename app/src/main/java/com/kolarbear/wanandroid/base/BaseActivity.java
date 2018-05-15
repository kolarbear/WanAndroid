package com.kolarbear.wanandroid.base;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.di.component.ActivityComponent;
import com.kolarbear.wanandroid.di.component.DaggerActivityComponent;
import com.kolarbear.wanandroid.di.module.ActivityModule;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/15.
 */

public abstract class BaseActivity<P extends BasePresenter> extends RxAppCompatActivity implements IView {


    protected Unbinder bind;
    @Inject
    protected P presenter;
    protected ActivityComponent component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);
        initActivityComponent();
        initInject();
        initView();
        initData();
    }

    private void initActivityComponent() {
        component = DaggerActivityComponent.builder()
                .appComponent(((App) getApplication()).appComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initInject();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        if (bind!=null)
        {
            bind.unbind();
            bind = null;
        }
        if (presenter!=null)
            presenter.destory();
        super.onDestroy();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNoNet() {

    }
}
