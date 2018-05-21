package com.kolarbear.wanandroid.base;

import com.kolarbear.wanandroid.api.ApiService;
import com.kolarbear.wanandroid.base.interfac.IPresenter;
import com.kolarbear.wanandroid.base.interfac.IView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BasePresenter<V extends IView> implements IPresenter {

    protected V view;

    @Inject
   protected ApiService service;

    protected WeakReference<V> reference;

    public BasePresenter(V view) {
        this.view = view;
        reference = new WeakReference<>(view);
    }

    protected V getView()
    {
        return reference.get();
    }

    public void destory()
    {
        reference.clear();
        reference = null;
    }

}
