package com.kolarbear.wanandroid.ui.category_articles;

import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.knowledge.CategoryBean;

/**
 * Created by Administrator on 2018/5/30.
 */

public interface ArticleListContract {
    public interface View extends IView{
        void showArticleList(BaseBean<CategoryBean> categoryBeanBaseBean,int type);
    }
}
