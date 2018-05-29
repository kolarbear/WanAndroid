package com.kolarbear.wanandroid.ui.knowledge;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

import javax.inject.Inject;

/**
 * 左边列表
 * Created by Administrator on 2018/5/22.
 */

public class LeftAdapter extends BaseQuickAdapter<KnowledgeBean,BaseViewHolder> {

    @Inject
    public LeftAdapter() {

        super(R.layout.item_knowledge);
    }

    private int selectedPosition;
    @Override
    protected void convert(BaseViewHolder helper, KnowledgeBean item) {
        if (item.isSelect())
        {
            helper.getView(R.id.rl_item).setSelected(true);
            helper.setTextColor(R.id.tv_category,  App.getContext().getResources().getColor(R.color.colorWhite));
        }
        else {
            helper.getView(R.id.rl_item).setSelected(false);
            helper.setTextColor(R.id.tv_category,  App.getContext().getResources().getColor(R.color.color333));
        }
        helper.setText(R.id.tv_category,item.getName());
    }

    public void chooseItem(int position)
    {
        if (position!=selectedPosition)
        {
            int prePosition = selectedPosition;
            selectedPosition = position;
            getData().get(selectedPosition).setSelect(true);
            getData().get(prePosition).setSelect(false);
            notifyDataSetChanged();
        }
    }

}
