package com.kolarbear.wanandroid.ui.category_articles;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by kolarbear on 2018/5/26.
 * Description: 某个分类下的所有文章
 */
@Route(path = "/category_articles/ArticleListActivity")
public class ArticleListActivity extends BaseActivity<ArticleListPresenter> implements ArticleListContract.View
,SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Autowired
    int cid;

    @Autowired
    String title;

    @Inject
    ArticleListAdapter adapter;

    private List<CategoryBean.DatasBean> datas;

    private int collectPosition;

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
        presenter.articleList(cid);
    }

    @Override
    protected void initView() {
        initRecyclerview();
        initToolbar();
    }

    private void initToolbar() {
        toolbar.setTitle(title);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CategoryBean.DatasBean datasBean = datas.get(position);
        ArticleActivity.startArticle(datasBean.getId(),datasBean.getTitle(),datasBean.getAuthor(),datasBean.getLink());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        collectPosition = position;
        if (this.adapter.getItem(position).isCollect())
        {
            presenter.cancelCollect(this.adapter.getItem(position).getId());
        }else {
            presenter.collect(this.adapter.getItem(position).getId());
        }
    }

    @Override
    public void showArticleList(BaseBean<CategoryBean> bean,int type) {
        datas = bean.data.getDatas();
        Utils.update(refreshLayout,adapter, datas,type);
    }

    @Override
    public void onRefresh() {
        presenter.refresh(cid);
    }

    @Override
    public void onLoadMoreRequested() {
        presenter.more(cid);
    }

    @Override
    public void collect(BaseBean result) {
        adapter.getItem(collectPosition).setCollect(true);
        adapter.setData(collectPosition,adapter.getItem(collectPosition));
        ToastUtils.showShort(getResources().getString(R.string.collect_success));
    }

    @Override
    public void cancelCollect(BaseBean result) {
        adapter.getItem(collectPosition).setCollect(true);
        adapter.setData(collectPosition,adapter.getItem(collectPosition));
        ToastUtils.showShort(getResources().getString(R.string.cancel_collect_success));
    }


}
