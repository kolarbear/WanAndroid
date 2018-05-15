package com.kolarbear.wanandroid.ui.main;

import android.util.Log;

import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.di.scope.ActivityScope;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/15.
 */
@ActivityScope
public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter{
    @Inject
    public MainPresenter(IView mainView) {
        super((MainContract.MainView)mainView);
    }

    @Override
    public void doSomething() {
        Log.e("MainPresenter", "doSomething: ");
        getView().showMsg();
    }
}
