package com.kolarbear.wanandroid.api;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.bean.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *  对Observer封装，提前处理响应结果
 *  Created by Administrator on 2018/5/31.
 */

public abstract class ApiResult<T> implements Observer<BaseBean<T>>{


    public abstract void onSuccess(BaseBean<T> t);

    public abstract void onFail(Throwable e);

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseBean<T> t) {
        if (t.errorCode==0)
        {
            onSuccess(t);
        }else {
            ToastUtils.showShort(t.errorMsg);
        }
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {

    }
}
