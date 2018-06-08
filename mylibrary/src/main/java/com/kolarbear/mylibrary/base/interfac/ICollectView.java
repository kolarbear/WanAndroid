package com.kolarbear.mylibrary.base.interfac;

import com.kolarbear.wanandroid.bean.BaseBean;

/**
 * Created by Administrator on 2018/5/31.
 */

public interface ICollectView extends IView{
    void collect(BaseBean result);
    void cancelCollect(BaseBean result);
}
