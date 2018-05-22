package com.kolarbear.wanandroid.ui.knowledge;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

/**
 * Created by Administrator on 2018/5/22.
 */

public class RightAdapter extends BaseQuickAdapter<KnowledgeBean.ChildrenBean,BaseViewHolder> {

    public RightAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean.ChildrenBean item) {

    }
}
