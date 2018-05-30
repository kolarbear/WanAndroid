package com.kolarbear.wanandroid.ui.category_articles;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;
import com.kolarbear.wanandroid.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kolarbear on 2018/5/26.
 * Description: 某个分类下的所有文章
 */
@Route(path = "/category_articles/ArticleListActivity")
public class ArticleListActivity extends BaseActivity<ArticleListPresenter> implements ArticleListContract.View
,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @Autowired
    int cid;

    @Inject
    ArticleListAdapter adapter;

    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(this);
        adapter.setOnLoadMoreListener(this,recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void showArticleList(BaseBean<CategoryBean> bean,int type) {
        Utils.update(refreshLayout,adapter,bean.data.getDatas(),type);
    }

    @Override
    public void onRefresh() {
        presenter.refresh(cid);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.more(cid);
    }
}
