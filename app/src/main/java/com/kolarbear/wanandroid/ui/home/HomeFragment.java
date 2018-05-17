package com.kolarbear.wanandroid.ui.home;

import android.os.Bundle;
import android.view.View;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/15.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView{


    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        presenter.example();
    }

}
