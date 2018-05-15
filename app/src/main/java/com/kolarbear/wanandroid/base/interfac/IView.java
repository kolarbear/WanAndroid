package com.kolarbear.wanandroid.base.interfac;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by Administrator on 2018/5/15.
 */

public interface IView {

    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();

    //显示进度中
    void showLoading();

    //隐藏进度
    void hideLoading();

    void showNoNet();

}
