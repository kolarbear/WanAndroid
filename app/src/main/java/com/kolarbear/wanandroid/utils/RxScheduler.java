package com.kolarbear.wanandroid.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 *  用于切换线程
 * Created by Administrator on 2018/5/21.
 */

public class RxScheduler {
    static final ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> ObservableTransformer<T,T> applySchedulers(){
         return (ObservableTransformer<T,T>)schedulersTransformer;
    }
}
