package com.kolarbear.wanandroid.ui.search;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.search.SearchResult;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 搜索页面
 * Created by Administrator on 2018/6/1.
 */
@Route(path = "/search/SearchActivity")
public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.View,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Autowired
    String k;

    @Inject
    SearchAdapter adapter;

    List<SearchResult.DatasBean> results;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected void initView() {
        results = new ArrayList<>();
        initToolbar();
        initList();
    }

    private void initList() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this,recyclerview);
        refreshLayout.setOnRefreshListener(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(k))
        {
            presenter.search(k);
        }
    }

    @Override
    public void collect(BaseBean result) {

    }

    @Override
    public void cancelCollect(BaseBean result) {

    }

    @Override
    public void showResult(SearchResult result,int type) {
        Utils.update(refreshLayout,adapter,result.getDatas(),type);
    }

    @Override
    public void onRefresh() {
        presenter.refresh(k);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.more(k);
    }
}
