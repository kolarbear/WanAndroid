package com.kolarbear.wanandroid.ui.search;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.search.SearchResult;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
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
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

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
    private SearchView searchView;
    private int position;
    private static final String TAG = "SearchActivity";
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
        adapter.setEmptyView(R.layout.activity_search_empty);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_id);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.refresh(query);
                k = query;
                KeyboardUtils.hideSoftInput(SearchActivity.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        if (TextUtils.isEmpty(k))
        {
            searchView.setIconified(false);
        }
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
            toolbar.setTitle(k);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(k))
        {
            toolbar.setTitle(k);
        }
    }

    @Override
    public void collect(BaseBean result) {
        if (result.errorCode==0)
        {
            this.adapter.getItem(position).setCollect(true);
            adapter.notifyItemChanged(position);
            ToastUtils.showShort("收藏成功");
        }
    }

    @Override
    public void cancelCollect(BaseBean result) {
        if (result.errorCode==0)
        {
            this.adapter.getItem(position).setCollect(false);
            adapter.notifyItemChanged(position);
            ToastUtils.showShort("取消收藏成功");
        }
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

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        this.position = position;
        if (this.adapter.getItem(position).isCollect())
        {
            presenter.cancelCollect(this.adapter.getItem(position).getId());
        }else {
            presenter.collect(this.adapter.getItem(position).getId());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SearchResult.DatasBean item = this.adapter.getItem(position);
        Log.e(TAG, "onItemClick: id>"+item.getId()+"title>"+item.getTitle()
        +"author>"+item.getAuthor()+"link>"+item.getLink());
        ArticleActivity
                .startArticle(item.getId()
                ,item.getTitle()
                ,item.getAuthor()
                ,item.getLink());
    }
}
