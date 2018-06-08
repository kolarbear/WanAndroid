package com.kolarbear.mylibrary.base;

import com.kolarbear.mylibrary.IService;
import com.kolarbear.mylibrary.base.interfac.IPresenter;
import com.kolarbear.mylibrary.base.interfac.IView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/15.
 */

public class BasePresenter<V extends IView> implements IPresenter {

    protected V view;
    protected int page;
    protected boolean refresh;
    @Inject
    protected IService service;

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

   /* *//**
     * 收藏
     * @param id
     *//*
    @LoginCheck(type = 0)
    public void collect(int id)
    {
        service.collect(id)
                .compose(getView().<BaseBean>bindToLife())
                .compose(RxScheduler.<BaseBean>applySchedulers())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                            if (baseBean.errorCode==0)
                            {
                                if (getView() instanceof ICollectView)
                                {
                                    ((ICollectView) getView()).collect(baseBean);
                                }
                            }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    *//**
     * 取消收藏
     * @param id
     *//*
    @LoginCheck(type = 1)
    public void cancelCollect(int id)
    {
        service.cancelCollect(id)
                .compose(getView().<BaseBean>bindToLife())
                .compose(RxScheduler.<BaseBean>applySchedulers())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        if (baseBean.errorCode==0)
                        {
                            if (getView() instanceof ICollectView)
                            {
                                ((ICollectView) getView()).cancelCollect(baseBean);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }*/

}
