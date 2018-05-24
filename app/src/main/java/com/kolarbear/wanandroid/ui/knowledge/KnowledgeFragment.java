package com.kolarbear.wanandroid.ui.knowledge;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
    private LinearLayoutManager leftLayoutManager;

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

                if (move)
                {
                    move = false;
                    int childCount = recyclerView.getChildCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int n = mTopPosition - firstVisibleItemPosition;
                    if (n<childCount)
                    {
                        View child = recyclerView.getChildAt(n);
                        int top = child.getTop();
                        recyclerView.scrollBy(0,top-40);
                    }
                }

                int position = layoutManager.findFirstVisibleItemPosition();
                for (int i = 0; i < positions.size(); i++) {
                    if (position==positions.get(i))
                    {
//                        leftList.scrollToPosition(i);
//                        leftLayoutManager.scrollToPositionWithOffset(i,0);
//                        leftLayoutManager.scrollToPosition(i);
                        leftAdapter.chooseItem(i);
//                        recyclerView.getChildAt(i).setFocusable(true);
//                       leftAdapter.setNewData(knowledgeBean);
                    }
                }
            }
        });
    }

    private int mTopPosition;
    private boolean move;
    private void initLeftList() {
//        ((SimpleItemAnimator)leftList.getItemAnimator()).setSupportsChangeAnimations(false);
//        leftList.getItemAnimator().setChangeDuration(0);
        leftLayoutManager = new LinearLayoutManager(getContext());
        leftList.setLayoutManager(leftLayoutManager);
        leftList.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTopPosition = positions.get(position);
                leftAdapter.chooseItem(position);
                rightList.scrollToPosition(mTopPosition);
                move = true;
            }
        });
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
