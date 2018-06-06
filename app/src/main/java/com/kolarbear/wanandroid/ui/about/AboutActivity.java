package com.kolarbear.wanandroid.ui.about;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/6/5.
 */

@Route(path = "/about/AboutActivity")
public class AboutActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarlayout)
    CollapsingToolbarLayout toolbarlayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle("关于我们");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        tvContent.setText(Html.fromHtml(getResources().getString(R.string.about_content)));
        tvContent.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }

}