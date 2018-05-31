package com.kolarbear.wanandroid.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.home.HomeArticle;
import com.kolarbear.wanandroid.bean.home.HomeBanner;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
import com.kolarbear.wanandroid.utils.GlideImageLoader;
import com.kolarbear.wanandroid.utils.Utils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

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

    @Inject
    HomeAdapter adapter;

    private Banner banner;

    private int collectPosition;
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
        articleList.setLayoutManager(new LinearLayoutManager(getContext()));
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

        presenter.loadBanner(); //加载banner数据
        presenter.loadHomeArticle();//加载文章列表
    }

    /**
     * 展示首页banner
     * @param homeBanners
     */
    @Override
    public void showBanner(List<HomeBanner> homeBanners) {
        List<String> titles = new ArrayList<>();
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < homeBanners.size(); i++) {
            titles.add(homeBanners.get(i).getTitle());
            imgs.add(homeBanners.get(i).getImagePath());
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
        presenter.refresh();
    }
    //加载更多
    @Override
    public void onLoadMoreRequested() {
        presenter.loadMore();
    }

    @Override
    public void showArticles(List<HomeArticle.DatasBean> datas, int type) {
        Utils.update(refreshLayout,adapter,datas,type);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        HomeArticle.DatasBean item = this.adapter.getItem(position);
        ArticleActivity.startArticle(item.getId(),item.getTitle(),item.getAuthor(),item.getLink());
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        collectPosition = position;
        if (!this.adapter.getItem(position).isCollect())
        {
            presenter.collect(this.adapter.getItem(position).getId());
        }else {
            presenter.cancelCollect(this.adapter.getItem(position).getId());
        }
            ToastUtils.showShort("id>"+this.adapter.getItem(position).getId());
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
