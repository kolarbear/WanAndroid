package com.kolarbear.wanandroid.ui.knowledge;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/22.
 */

public class RightAdapter extends BaseQuickAdapter<KnowledgeBean.ChildrenBean,BaseViewHolder> {

    @Inject
    public RightAdapter() {
        super(R.layout.item_knowledge_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean.ChildrenBean item) {
        helper.setText(R.id.tv_name,item.getName());
    }
}
