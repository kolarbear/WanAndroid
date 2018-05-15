package com.kolarbear.wanandroid.ui.main;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void initData() {
        presenter.doSomething();
    }

    @Override
    protected void initView() {
        toolbar.setTitle("玩Android");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.layout_open, R.string.layout_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//改變圖標
        drawerToggle.syncState();////show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));

    }

    @Override
    public void showMsg() {
        ToastUtils.showShort("调到了吗？");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        component.inject(this);
    }
}


