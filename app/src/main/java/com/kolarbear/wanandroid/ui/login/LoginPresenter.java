package com.kolarbear.wanandroid.ui.login;

import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.utils.RxScheduler;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/5/28.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> {

    @Inject
    public LoginPresenter(IView view) {
        super((LoginContract.View)view);
    }


    public void login(String username,String password)
    {
        service.login(username,password)
                .compose(getView().<BaseBean>bindToLife())
                .compose(RxScheduler.<BaseBean>applySchedulers())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        getView().loginResult(baseBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void register(String username,String password){
        service.register(username,password,password)
                .compose(getView().<BaseBean>bindToLife())
                .compose(RxScheduler.<BaseBean>applySchedulers())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                        getView().registerResult(baseBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
