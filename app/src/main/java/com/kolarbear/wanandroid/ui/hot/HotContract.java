package com.kolarbear.wanandroid.ui.hot;

import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.hot.HotBean;
import com.kolarbear.wanandroid.bean.hot.WebsiteBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */

public interface HotContract {
    interface View extends IView{
        void showHot(List<HotBean> data);
        void showSites(List<WebsiteBean> data);
    }
}
