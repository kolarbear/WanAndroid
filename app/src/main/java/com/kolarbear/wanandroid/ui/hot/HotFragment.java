package com.kolarbear.wanandroid.ui.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.hot.HotBean;
import com.kolarbear.wanandroid.bean.hot.WebsiteBean;
import com.kolarbear.wanandroid.greendao.HotBeanDao;
import com.kolarbear.wanandroid.greendao.WebsiteBeanDao;
import com.kolarbear.wanandroid.ui.article.ArticleActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 热搜
 * Created by Administrator on 2018/6/1.
 */

public class HotFragment extends BaseFragment<HotPresenter> implements HotContract.View{

    @BindView(R.id.flowlayout_search)
    TagFlowLayout flowlayoutSearch;
    @BindView(R.id.flowlayout_use)
    TagFlowLayout flowlayoutUse;

    List<HotBean> hots;
    List<WebsiteBean> webs;
    private HotTagAdapter hotTagAdapter;
    private WebsTagAdapter websTagAdapter;
    private static final String TAG = "HotFragment";
    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    public static HotFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        hots = new ArrayList<>();
        webs = new ArrayList<>();
    }

    /**
     * 在 {@link #onSupportVisible}后调用
     * @param savedInstanceState
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        hotTagAdapter = new HotTagAdapter(getContext(), hots);
        websTagAdapter = new WebsTagAdapter(getContext(), webs);
        flowlayoutSearch.setAdapter(hotTagAdapter);
        flowlayoutUse.setAdapter(websTagAdapter);
        if (NetworkUtils.isConnected())
        {
            presenter.hotWords();
            presenter.websites();
        }else {
            HotBeanDao hotBeanDao = App.getApp().getDaoSession().getHotBeanDao();
            showHot(hotBeanDao.loadAll());
            WebsiteBeanDao websiteBeanDao = App.getApp().getDaoSession().getWebsiteBeanDao();
            showSites(websiteBeanDao.loadAll());
        }

        setListener();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    private void setListener() {

        flowlayoutSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                //热词
                ARouter.getInstance().build("/search/SearchActivity")
                        .withString("k",hots.get(position).getName())
                        .navigation();
                return true;
            }
        });
        flowlayoutUse.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                WebsiteBean bean = webs.get(position);
                //常用网站
                ArticleActivity.startArticle(bean.getId(),bean.getName(),bean.getName(),bean.getLink());
                return true;
            }
        });
    }

    @Override
    public void showHot(List<HotBean> data) {
        if (data!=null)
        {
            hots.addAll(data);
        }
        hotTagAdapter.notifyDataChanged();
        if (NetworkUtils.isConnected())
        {
            HotBeanDao hotBeanDao = App.getApp().getDaoSession().getHotBeanDao();
            hotBeanDao.deleteAll();
            for (int i = 0; i < data.size(); i++) {
                hotBeanDao.insert(data.get(i));
            }
        }
    }

    @Override
    public void showSites(List<WebsiteBean> data) {
        if (data!=null)
        {
            webs.addAll(data);
        }
        websTagAdapter.notifyDataChanged();
        if (NetworkUtils.isConnected())
        {
            WebsiteBeanDao websiteBeanDao = App.getApp().getDaoSession().getWebsiteBeanDao();
            websiteBeanDao.deleteAll();
            for (int i = 0; i < data.size(); i++) {
                websiteBeanDao.insert(data.get(i));
            }
        }
    }
}
