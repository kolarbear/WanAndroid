package com.kolarbear.wanandroid.ui.knowledge;

import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.knowledge.KnowledgeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public interface KnowledgeContract {

    public interface View extends IView{
        void showKnowledgeTree(List<KnowledgeBean> knowledgeBean);
    }
}
