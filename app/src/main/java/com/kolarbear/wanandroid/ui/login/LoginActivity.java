package com.kolarbear.wanandroid.ui.login;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.kolarbear.wanandroid.R;
import com.kolarbear.wanandroid.base.BaseActivity;
import com.kolarbear.wanandroid.bean.BaseBean;

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

    }

    @Override
    protected void initView() {
//        usernameLayout.setError("用户名错误");
//        usernameLayout.setErrorEnabled(true);
//        passwordLayout.setError("密码错误");
//        passwordLayout.setErrorEnabled(true);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void loginResult(BaseBean result) {

    }

    @Override
    public void registerResult(BaseBean result) {

    }

    @OnClick({R.id.btRegister, R.id.btLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btRegister:
                break;
            case R.id.btLogin:
                break;
        }
    }
}
