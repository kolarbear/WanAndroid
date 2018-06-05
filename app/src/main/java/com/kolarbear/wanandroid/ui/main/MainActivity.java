package com.kolarbear.wanandroid.ui.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.ui.home.HomeFragment;
import com.kolarbear.wanandroid.ui.hot.HotFragment;
import com.kolarbear.wanandroid.utils.UserController;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView, NavigationView.OnNavigationItemSelectedListener {

    private long mExitTime;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    private TextView tvLogin; //登录的用户
    private TextView tvName; //登录的用户
    private LoginBroadcastReceiver receiver;

    @Override
    protected void initData() {
        presenter.doSomething();
        //注册广播接收者
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter("loginSuccess");
        receiver = new LoginBroadcastReceiver();
        instance.registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void initView() {

        setToolbar();
        setDrawerLayout();

        if (findFragment(MainFragment.class) == null)
        {
            loadRootFragment(R.id.contentPanel,MainFragment.newInstance());
        }


    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.layout_open, R.string.layout_close);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);//改變圖標
        drawerToggle.syncState();////show the default icon and sync the DrawerToggle state,如果你想改变图标的话，这句话要去掉。这个会使用默认的三杠图标
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(this);
//        ImageView ivPortrait = navigationView.getHeaderView(0).findViewById(R.id.iv_portrait);
        tvLogin = navigationView.getHeaderView(0).findViewById(R.id.tv_login);
        tvName = navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        if (UserController.getInstance().isLogin())
        {
            tvLogin.setText("退出登录");
            tvName.setText(UserController.getInstance().getUser());
        }
        tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!UserController.getInstance().isLogin())
                    ARouter.getInstance().build("/login/LoginActivity")
                            .navigation(MainActivity.this,2);
                    else //退出登录;
                    {
                        UserController.getInstance().exit();
                        tvName.setText("未登录");
                        tvLogin.setText("点击登录");
                    }
                }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2)
        {
            if (resultCode==1)
            {

            }
        }
    }

    private void setToolbar() {
        toolbar.setTitle("玩Android");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuHot:
//                ToastUtils.showShort("热点");
                MainFragment topFragment = (MainFragment) getTopFragment();
                topFragment.startHot();
                break;
            case R.id.menuSearch:
                ARouter.getInstance()
                        .build("/search/SearchActivity")
                        .navigation();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showMsg() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
            {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort(R.string.exit_system);
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId)
        {
            case R.id.nav_like:
                if (!UserController.getInstance().isLogin())
                {
                    ToastUtils.showShort("请先登录");
                }else {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    ARouter.getInstance().build("/collectlist/CollectList")
                            .navigation();
                }
                break;
            case R.id.nav_about:
//                ToastUtils.showShort("关于我");
                ARouter.getInstance()
                        .build("/about/AboutActivity")
                        .navigation();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }

        return false;
    }

    public void setToolbarText(String text)
    {
        toolbar.setTitle(text);
    }


    class LoginBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("loginSuccess"))
            {
                String name = intent.getStringExtra("name");
//                ToastUtils.showShort("MainActivity receive Login Success");
                //登录成功
                tvName.setText(name);
                tvLogin.setText("退出登录");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }
}


