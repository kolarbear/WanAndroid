package com.kolarbear.wanandroid.ui.category_articles;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArticleListAdapter extends BaseQuickAdapter<CategoryBean.DatasBean,BaseViewHolder>
{
    @Inject
    public ArticleListAdapter() {
        super(R.layout.item_home_article,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryBean.DatasBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_date,item.getNiceDate())
                .setText(R.id.tv_article_category,item.getChapterName())
                .setText(R.id.tv_article_title,item.getTitle());
    }
}
