package com.kolarbear.wanandroid.ui.knowledge;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/22.
 */

public class LeftAdapter extends BaseQuickAdapter<KnowledgeBean,BaseViewHolder> {

    @Inject
    public LeftAdapter() {
        super(R.layout.item_knowledge);
    }

    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean item) {
        helper.setText(R.id.tv_category,item.getName());
    }
}
