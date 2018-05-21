package com.kolarbear.wanandroid.ui.home;

import com.kolarbear.wanandroid.base.interfac.IPresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.home.HomeBanner;

import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface HomeContract {
    interface IHomeView extends IView
    {
        void showBanner(List<HomeBanner> homeBanners);
    }
    interface IHomePresenter extends IPresenter{

    }
}
