package com.kolarbear.wanandroid.ui.login;

import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;

/**
 * Created by Administrator on 2018/5/28.
 */

public interface LoginContract {

    interface View extends IView{
        void loginResult(BaseBean result);
        void registerResult(BaseBean result);
    }
}
