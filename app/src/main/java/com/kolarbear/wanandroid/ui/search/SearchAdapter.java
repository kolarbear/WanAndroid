package com.kolarbear.wanandroid.ui.search;

import android.text.Html;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.bean.search.SearchResult;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/1.
 */

public class SearchAdapter extends BaseQuickAdapter<SearchResult.DatasBean,BaseViewHolder> {

    @Inject
    public SearchAdapter() {
        super(R.layout.item_home_article, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResult.DatasBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor())
                .setText(R.id.tv_date,item.getNiceDate())
                .setText(R.id.tv_article_category,item.getChapterName())
                .setText(R.id.tv_article_title, Html.fromHtml(item.getTitle()));
        helper.addOnClickListener(R.id.iv_collect);
        helper.setImageResource(R.id.iv_collect, item.isCollect()?R.drawable.ic_action_like:R.drawable.ic_action_no_like);
    }
}
