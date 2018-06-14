package com.kolarbear.wanandroid.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.home.Articles;
import com.kolarbear.wanandroid.bean.home.HomeBanner;
import com.kolarbear.wanandroid.greendao.ArticlesDao;
import com.kolarbear.wanandroid.greendao.HomeBannerDao;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
import com.kolarbear.wanandroid.utils.GlideImageLoader;
import com.kolarbear.wanandroid.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首页
 * Created by Administrator on 2018/5/15.
 */

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.IHomeView ,SwipeRefreshLayout.OnRefreshListener
, HomeAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemClickListener
        ,BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.articleList)
    RecyclerView articleList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.floatbar)
    FloatingActionButton fab;

    @Inject
    HomeAdapter adapter;

    private Banner banner;

    private int collectPosition;

    private int data_source = 0;

    private static final int TYPE_NET = 0;
    private static final int TYPE_DB = 1;

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

        /**
         * 设置Recyclerview
         */
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        articleList.setLayoutManager(manager);
        articleList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        /**
         *  设置banner Header
         */
        View mHomeHeader = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_header, null);
        banner = mHomeHeader.findViewById(R.id.banner);
        adapter.addHeaderView(mHomeHeader);
        adapter.setOnLoadMoreListener(this,articleList);
        refreshLayout.setOnRefreshListener(this);


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        loadData();
    }

    /**
     * 获取数据策略
     */
    private void loadData() {
        //如果网络可用，就获取网络数据，并更新本地数据库
        if(NetworkUtils.isConnected())
        {
            data_source = TYPE_NET;
            presenter.loadBanner(); //加载banner数据
            presenter.loadHomeArticle();//加载文章列表
        }else {//从数据库中获取
            data_source = TYPE_DB;
            HomeBannerDao bannerDao = App.getApp().getDaoSession().getHomeBannerDao();
            List<HomeBanner> homeBanners = bannerDao.loadAll();
            showBanner(homeBanners);
            ArticlesDao articlesDao = App.getApp().getDaoSession().getArticlesDao();
            List<Articles> articles = articlesDao.loadAll();
            showArticles(articles,Utils.TYPE_REFRESH);
        }
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @OnClick(R.id.floatbar)
    public void onClick(View view)
    {
        articleList.scrollToPosition(0);
//        fab.hide();
    }

    /**
     * 展示首页banner
     * @param homeBanners
     */
    @Override
    public void showBanner(List<HomeBanner> homeBanners) {
            List<String> titles = new ArrayList<>();
            List<String> imgs = new ArrayList<>();
        if (data_source == TYPE_NET)//如果从网上获取的数据，那就更新数据库
        {
            HomeBannerDao bannerDao = App.getApp().getDaoSession().getHomeBannerDao();
            bannerDao.deleteAll();
            for (int i = 0; i < homeBanners.size(); i++) {
                titles.add(homeBanners.get(i).getTitle());
                imgs.add(homeBanners.get(i).getImagePath());
                bannerDao.insert(homeBanners.get(i));
            }
        }else {//从数据库中获取的那就直接展示
            for (int i = 0; i < homeBanners.size(); i++) {
                titles.add(homeBanners.get(i).getTitle());
                imgs.add(homeBanners.get(i).getImagePath());
            }
        }
        banner.setImages(imgs)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setImageLoader(new GlideImageLoader())
                .start();
    }
    //刷新
    @Override
    public void onRefresh() {
        if (data_source==TYPE_NET)
        presenter.refresh();
    }
    //加载更多
    @Override
    public void onLoadMoreRequested() {
        if (data_source==TYPE_NET)
        presenter.loadMore();
    }

    @Override
    public void showArticles(List<Articles> datas, int type) {
        if (data_source==TYPE_NET)//如果是从网上获取数据，那就更新数据库
        {
            ArticlesDao articlesDao = App.getApp().getDaoSession().getArticlesDao();
            articlesDao.deleteAll();
            for (Articles articles:datas) {
                articlesDao.insert(articles);
            }
        }
        Utils.update(refreshLayout,adapter,datas,type);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Articles item = this.adapter.getItem(position);
        ArticleActivity.startArticle(item.getId(),item.getTitle(),item.getAuthor(),item.getLink());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId())
        {
            case R.id.tv_article_category:
                Articles item = this.adapter.getItem(position);
                ARouter.getInstance()
                        .build("/category_articles/ArticleListActivity")
                        .withInt("cid",item.getChapterId())
                        .withString("title",item.getChapterName())
                        .navigation();
                break;
            case R.id.iv_collect:
                collectPosition = position;
                if (!this.adapter.getItem(position).isCollect())
                {
                    presenter.collect(this.adapter.getItem(position).getId());
                }else {
                    presenter.cancelCollect(this.adapter.getItem(position).getId());
                }
                break;
        }
    }

    @Override
    public void collect(BaseBean result) {
        adapter.getItem(collectPosition).setCollect(true);
        adapter.setData(collectPosition,adapter.getItem(collectPosition));
    }

    @Override
    public void cancelCollect(BaseBean result) {
        adapter.getItem(collectPosition).setCollect(false);
        adapter.setData(collectPosition,adapter.getItem(collectPosition));
    }
}
