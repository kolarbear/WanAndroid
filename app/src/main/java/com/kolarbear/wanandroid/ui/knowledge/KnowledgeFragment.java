package com.kolarbear.wanandroid.ui.knowledge;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;
import com.kolarbear.wanandroid.utils.Utils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/5/21.
 */

public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements KnowledgeContract.View
       ,SwipeRefreshLayout.OnRefreshListener{


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
    private List<Integer> reversePositions;
    private List<String> headers;
    private LinearLayoutManager leftLayoutManager;
    private LinearLayoutManager rightManager;

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
        reversePositions = new ArrayList<>();
        headers = new ArrayList<>();
        refreshLayout.setOnRefreshListener(this);
        presenter.getKnowledgeTree();
    }

    private void initRightList() {

        rightManager = new LinearLayoutManager(getContext());
        rightList.setLayoutManager(rightManager);
        rightList.setAdapter(rightAdapter);
        rightList.addItemDecoration(new StickyRecyclerHeadersDecoration(rightAdapter));
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
                    int firstVisibleItemPosition = rightManager.findFirstVisibleItemPosition();
                    int n = mTopPosition - firstVisibleItemPosition;
                    if (n<childCount)
                    {
                        View child = recyclerView.getChildAt(n);
                        int top = child.getTop();
                        recyclerView.smoothScrollBy(0,top-dip2px(getContext(),25));
                    }
                }
                if (!clickFromLeft)
                {
                    if (dy>0)
                    {
                        int position = rightManager.findFirstVisibleItemPosition();
                        for (int i = 0; i < positions.size(); i++) {
                            if (position==positions.get(i))
                            {
                                leftAdapter.chooseItem(i);
                                //                            leftList.scrollToPosition(i+3);
                                leftLayoutManager.scrollToPositionWithOffset(i,0);
                            }
                        }
                    }else {
                        int position = rightManager.findFirstVisibleItemPosition();
                        for (int i = 0; i < reversePositions.size(); i++) {
                            if (position==reversePositions.get(i))
                            {
                                leftAdapter.chooseItem(i);
                                //                            leftList.scrollToPosition(i-5);
                                leftLayoutManager.scrollToPositionWithOffset(i,0);
                            }
                        }
                    }
                }else {
                    clickFromLeft = false;
                }


            }
        });
    }
    /**
     * 根据手机分辨率从dp转成px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    private int mTopPosition;
    private boolean move;
    private boolean clickFromLeft = false;
    private void initLeftList() {
        leftLayoutManager = new LinearLayoutManager(getContext());
        leftList.setLayoutManager(leftLayoutManager);
        leftList.setAdapter(leftAdapter);
        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mTopPosition = positions.get(position);
                leftAdapter.chooseItem(position);
                clickFromLeft = true;
                moveToPosition(mTopPosition);
            }
        });
    }

    private void moveToPosition(int n)
    {
        int firstVisibleItemPosition = rightManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = rightManager.findLastVisibleItemPosition();
        if (n <= firstVisibleItemPosition)
        {
            rightList.scrollToPosition(mTopPosition);
        }else if (n <= lastVisibleItemPosition)
        {
            //当要置顶的项已经在屏幕上显示时
            int top = rightList.getChildAt(n - firstVisibleItemPosition).getTop();
            rightList.smoothScrollBy(0,top-dip2px(getContext(),25));
        }else {
            rightList.scrollToPosition(mTopPosition);
            move = true;
        }
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
        prepareReversePosition();
        knowledgeBean.get(0).setSelect(true);
        Utils.update(refreshLayout,leftAdapter,knowledgeBean,0);
        rightAdapter.setHeaders(headers);
        rightAdapter.setDatas(childrens);
    }

    private void prepareRightData(List<KnowledgeBean> knowledgeBean) {
        for (int i = 0; i < knowledgeBean.size(); i++) {
            List<KnowledgeBean.ChildrenBean> children = knowledgeBean.get(i).getChildren();
            childrens.addAll(children);
            headers.add(knowledgeBean.get(i).getName());
        }
    }

    /**
     * 记录 每个tag 第一个位置
     */
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

    /**
     * 记录每个tag 最后一个位置
     */
    private void prepareReversePosition()
    {
        for (int i = childrens.size(); i > 0; i--) {
            if (i==childrens.size())
            {
                reversePositions.add(i);
                continue;
            }
            if (i>1)
            {
                if (childrens.get(i).getParentChapterId()!=childrens.get(i-1).getParentChapterId())
                {
                    reversePositions.add(i-1);
                }
            }
        }
        Collections.reverse(reversePositions);
    }


    @Override
    public void onRefresh() {
        presenter.getKnowledgeTree();
    }
}
