package com.kolarbear.wanandroid.ui.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;
import com.kolarbear.wanandroid.bean.login.LoginBean;
import com.kolarbear.wanandroid.utils.UserController;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by Administrator on 2018/5/24.
 */
@Route(path = "/login/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.usernameLayout)
    TextInputLayout usernameLayout;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.passwordLayout)
    TextInputLayout passwordLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        component.inject(this);
    }

    @Override
    protected void initView() {
//
    }

    @Override
    protected void initData() {

    }


    @Override
    public void loginResult(BaseBean<LoginBean> result) {
        if (result.errorCode==0)
        {
            ToastUtils.showShort("登录成功");
            UserController.getInstance().setUser(result.data.getUsername());
            setResult(1,getIntent().putExtra("name",result.data.getUsername()));
            finish();
        }else {
            ToastUtils.showShort(result.errorMsg);
        }
    }

    @Override
    public void registerResult(BaseBean result) {

    }

    @OnClick({R.id.btRegister, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btRegister:
                presenter.register(username.getText().toString(),password.getText().toString());
                break;
            case R.id.btLogin:
                presenter.login(username.getText().toString(),password.getText().toString());
                break;
        }
    }
}
