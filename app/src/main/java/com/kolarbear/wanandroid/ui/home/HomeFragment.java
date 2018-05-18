package com.kolarbear.wanandroid.ui.home;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/15.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView {


    @BindView(R.id.articleList)
    RecyclerView articleList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigation;

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
