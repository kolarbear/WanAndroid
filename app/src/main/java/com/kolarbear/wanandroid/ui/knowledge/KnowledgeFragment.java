package com.kolarbear.wanandroid.ui.knowledge;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/21.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View {


    @BindView(R.id.leftList)
    RecyclerView leftList;
    @BindView(R.id.rightList)
    RecyclerView rightList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    LeftAdapter leftAdapter;

    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    public static KnowledgeFragment newInstance() {
        Bundle args = new Bundle();
        KnowledgeFragment fragment = new KnowledgeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

        initLeftList();


        presenter.getKnowledgeTree();
    }

    private void initLeftList() {
        leftList.setLayoutManager(new LinearLayoutManager(getContext()));
        leftList.setAdapter(leftAdapter);
    }

    /**
     * 展示知识体系列表
     *
     * @param knowledgeBean
     */
    @Override
    public void showKnowledgeTree(List<KnowledgeBean> knowledgeBean) {
        Utils.update(refreshLayout,leftAdapter,knowledgeBean,0);
        List<KnowledgeBean.ChildrenBean> children = knowledgeBean.get(0).getChildren();

    }
}
