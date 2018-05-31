package com.kolarbear.wanandroid.ui.collectlist;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.collect.CollectArticle;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/31.
 */

public class CollectListAdapter extends BaseQuickAdapter<CollectArticle.DatasBean,BaseViewHolder>{

    @Inject
    public CollectListAdapter() {
        super(R.layout.item_home_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectArticle.DatasBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_date,item.getNiceDate())
                .setText(R.id.tv_article_category,item.getChapterName())
                .setText(R.id.tv_article_title,item.getTitle());
        helper.addOnClickListener(R.id.iv_collect);
        helper.setImageResource(R.id.iv_collect, R.drawable.ic_action_like);

    }
}
