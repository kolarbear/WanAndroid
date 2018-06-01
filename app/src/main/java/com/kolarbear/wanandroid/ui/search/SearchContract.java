package com.kolarbear.wanandroid.ui.search;

import com.kolarbear.wanandroid.base.interfac.ICollectView;
import com.kolarbear.wanandroid.bean.search.SearchResult;

/**
 * Created by Administrator on 2018/6/1.
 */

public interface SearchContract {

    interface View extends ICollectView{
        void showResult(SearchResult result, int type);
    }
}
