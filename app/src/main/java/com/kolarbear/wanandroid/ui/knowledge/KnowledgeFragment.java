package com.kolarbear.wanandroid.ui.knowledge;

import android.os.Bundle;
import android.view.View;

import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseFragment;

/**
 * Created by Administrator on 2018/5/21.
 */

public class KnowledgeFragment extends BaseFragment {
    @Override
    protected void initInject() {

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

    }
}
