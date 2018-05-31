package com.kolarbear.wanandroid.ui.collectlist;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.collect.CollectArticle;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 我的收藏列表
 * Created by Administrator on 2018/5/31.
 */
@Route(path = "/collectlist/CollectList")
public class CollectListActivity extends BaseActivity<CollectListPresenter>
        implements CollectListContract.View,SwipeRefreshLayout.OnRefreshListener
        ,BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener
        ,BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    CollectListAdapter adapter;
    private List<CollectArticle.DatasBean> datas;

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
        initRecyclerview();
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle("我喜欢的");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerview() {
        refreshLayout.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this,recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CollectArticle.DatasBean datasBean = datas.get(position);
        ArticleActivity.startArticle(datasBean.getId(),datasBean.getTitle(),datasBean.getAuthor(),datasBean.getLink());

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        presenter.cancelCollect(this.adapter.getItem(position).getOriginId());
        adapter.remove(position);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.more();
    }

    @Override
    protected void initData() {
        presenter.refresh();
    }

    @Override
    public void showList(BaseBean<CollectArticle> t,int type) {
        datas = t.data.getDatas();
        Utils.update(refreshLayout,adapter, datas,type);
    }

    @Override
    public void collect(BaseBean result) {

    }

    @Override
    public void cancelCollect(BaseBean result) {

    }
}
