package com.kolarbear.wanandroid.ui.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.hot.HotBean;
import com.kolarbear.wanandroid.bean.hot.WebsiteBean;
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

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        hotTagAdapter = new HotTagAdapter(getContext(), hots);
        websTagAdapter = new WebsTagAdapter(getContext(), webs);
        flowlayoutSearch.setAdapter(hotTagAdapter);
        flowlayoutUse.setAdapter(websTagAdapter);
        presenter.hotWords();
        presenter.websites();
        setListener();
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
    }

    @Override
    public void showSites(List<WebsiteBean> data) {
        if (data!=null)
        {
            webs.addAll(data);
        }
        websTagAdapter.notifyDataChanged();
    }
}
