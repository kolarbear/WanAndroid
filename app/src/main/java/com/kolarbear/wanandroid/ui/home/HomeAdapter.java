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
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_date,item.getNiceDate())
                .setText(R.id.tv_article_category,item.getChapterName())
                .setText(R.id.tv_article_title,item.getTitle());
    }
}
