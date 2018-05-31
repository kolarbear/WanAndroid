package com.kolarbear.wanandroid.ui.collectlist;

import com.kolarbear.wanandroid.base.interfac.ICollectView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.collect.CollectArticle;

/**
 * Created by Administrator on 2018/5/31.
 */

public interface CollectListContract {

    interface View extends ICollectView{
        void showList(BaseBean<CollectArticle> t,int type);
    }
}
