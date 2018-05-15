package com.kolarbear.wanandroid.ui.main;

import com.kolarbear.wanandroid.base.interfac.IPresenter;
import com.kolarbear.wanandroid.base.interfac.IView;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface MainContract {

    interface MainView extends IView{
        void showMsg();
    }

    interface MainPresenter extends IPresenter{
        void doSomething();
    }
}
