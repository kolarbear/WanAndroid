package com.kolarbear.wanandroid.ui.knowledge;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.kolarbear.wanandroid.utils.StickDecoration;
import com.kolarbear.wanandroid.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/21.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View ,StickDecoration.OnTagListener{


    @BindView(R.id.leftList)
    RecyclerView leftList;
    @BindView(R.id.rightList)
    RecyclerView rightList;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    @Inject
    LeftAdapter leftAdapter;

    @Inject
    RightAdapter rightAdapter;

    private List<KnowledgeBean.ChildrenBean> childrens;
    private List<KnowledgeBean> knowledgeBean;
    private List<Integer> positions;
    @Override
    protected void initInject() {
        component.inject(this);
    }

    private static final String TAG = "KnowledgeFragment";
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
        initRightList();
        childrens = new ArrayList<>();
        positions = new ArrayList<>();
        presenter.getKnowledgeTree();
    }

    private void initRightList() {
        rightList.addItemDecoration(new StickDecoration(this));
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rightList.setLayoutManager(layoutManager);
        rightList.setAdapter(rightAdapter);
        rightList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = layoutManager.findFirstVisibleItemPosition();
                for (int i = 0; i < positions.size(); i++) {
                    if (position==positions.get(i))
                    {
                        for (int j = 0; j < knowledgeBean.size(); j++) {
                            if (j==i)
                            {
                                knowledgeBean.get(j).setSelect(true);
                            }else {
                                knowledgeBean.get(j).setSelect(false);
                            }
                        }
                        Utils.update(refreshLayout,leftAdapter,knowledgeBean,0);
                    }
                }
            }
        });
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
        this.knowledgeBean = knowledgeBean;
        prepareRightData(knowledgeBean);
        prepareTagPosition();
        knowledgeBean.get(0).setSelect(true);
        Utils.update(refreshLayout,leftAdapter,knowledgeBean,0);
        Utils.update(refreshLayout,rightAdapter,childrens,0);
    }

    private void prepareRightData(List<KnowledgeBean> knowledgeBean) {
        for (int i = 0; i < knowledgeBean.size(); i++) {
            List<KnowledgeBean.ChildrenBean> children = knowledgeBean.get(i).getChildren();
            childrens.addAll(children);
        }
    }

    private void prepareTagPosition()
    {
        for (int i = 0; i < childrens.size(); i++) {
            if (i==0)
            {
                positions.add(i);
                continue;
            }
            if (i<childrens.size()-1)
            {
                if (childrens.get(i).getParentChapterId()!=childrens.get(i+1).getParentChapterId())
                {
                    positions.add(i+1);
                }
            }
        }
    }

    @Override
    public String getCurrentTag(int position) {
        int parentChapterId = childrens.get(position).getParentChapterId();
        String tag = null;
        for (int i = 0; i < knowledgeBean.size(); i++) {
            if (knowledgeBean.get(i).getId()==parentChapterId)
            {
                tag = knowledgeBean.get(i).getName();
            }
        }
        return tag == null ? "" : tag;
    }
}
