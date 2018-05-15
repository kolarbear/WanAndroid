package com.kolarbear.wanandroid.ui.home;

import android.util.Log;

import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.di.scope.FragmentScope;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/15.
 */
@FragmentScope
public class HomePresenter extends BasePresenter<HomeContract.IHomeView> {

    @Inject
    public HomePresenter(IView view) {
        super((HomeContract.IHomeView)view);
    }

    public void example()
    {
        Log.e("HomePresenter", "example: 哈？");
    }
}
