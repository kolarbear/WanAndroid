package com.kolarbear.wanandroid.ui.login;

import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.api.ApiResult;
import com.kolarbear.wanandroid.base.BasePresenter;
import com.kolarbear.wanandroid.base.interfac.IView;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.login.LoginBean;
import com.kolarbear.wanandroid.utils.RxScheduler;

import javax.inject.Inject;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
        if (isLegal(username,password))
        {
            service.login(username,password)
                    .compose(getView().<BaseBean<LoginBean>>bindToLife())
                    .compose(RxScheduler.<BaseBean<LoginBean>>applySchedulers())
                    .subscribe(new Consumer<BaseBean<LoginBean>>() {
                        @Override
                        public void accept(BaseBean<LoginBean> baseBean) throws Exception {
                            getView().loginResult(baseBean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });
        }
    }

    /**
     * 检查账户密码是否合法
     * @param username
     * @param password
     * @return
     */
    private boolean isLegal(String username,String password)
    {
        if (!TextUtils.isEmpty(username))
        {
            if (!TextUtils.isEmpty(password))
            {
                return true;
            }else {
                ToastUtils.showShort("密码不能为空！");
                return false;
            }
        }else {
            ToastUtils.showShort("用户名不能为空！");
            return false;
        }
    }

    /**
     * 注册成功后自动登录
     * @param username
     * @param password
     */
    public void register(final String username, final String password){
        if (isLegal(username,password))
        {
            service.register(username,password,password)
                    .flatMap(new Function<BaseBean, ObservableSource<BaseBean<LoginBean>>>() {
                        @Override
                        public ObservableSource<BaseBean<LoginBean>> apply(BaseBean baseBean) throws Exception {
                            if (baseBean.errorCode==0)
                            return service.login(username,password);
                            else ToastUtils.showShort(baseBean.errorMsg);
                            return null;
                        }
                    })
                    .compose(getView().<BaseBean<LoginBean>>bindToLife())
                    .compose(RxScheduler.<BaseBean<LoginBean>>applySchedulers())
                    .subscribe(new ApiResult<LoginBean>() {
                        @Override
                        public void onSuccess(BaseBean<LoginBean> t) {
                            getView().registerResult(t);
                        }

                        @Override
                        public void onFail(Throwable e) {

                        }
                    });
                   /* .compose(getView().<BaseBean<LoginBean>>bindToLife())
                    .compose(RxScheduler.<BaseBean<LoginBean>>applySchedulers())
                    .subscribe(new Consumer<BaseBean<LoginBean>>() {
                        @Override
                        public void accept(BaseBean baseBean) throws Exception {
                            getView().registerResult(baseBean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                        }
                    });*/
        }
    }
}
