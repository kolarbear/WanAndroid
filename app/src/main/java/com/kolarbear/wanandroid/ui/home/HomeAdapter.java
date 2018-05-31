package com.kolarbear.wanandroid.ui.home;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.home.HomeArticle;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/21.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeArticle.DatasBean,BaseViewHolder> {

    @Inject
    public HomeAdapter() {
        super(R.layout.item_home_article,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticle.DatasBean item) {
        helper.addOnClickListener(R.id.iv_collect);
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_date,item.getNiceDate())
                .setText(R.id.tv_article_category,item.getChapterName())
                .setText(R.id.tv_article_title,item.getTitle());
        helper.setImageResource(R.id.iv_collect,item.isCollect() ? R.drawable.ic_action_like : R.drawable.ic_action_no_like);
    }
}
