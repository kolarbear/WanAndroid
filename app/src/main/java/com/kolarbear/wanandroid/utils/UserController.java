package com.kolarbear.wanandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.kolarbear.wanandroid.app.App;
import com.kolarbear.wanandroid.constant.Constant;


/**
 * Created by Administrator on 2017/12/25.
 */

public class UserController {

    private static UserController user;

    private SharedPreferences sp;

    public static UserController getInstance() {
        if (user == null) {
            user = new UserController();
        }
        return user;
    }

    private UserController() {
        sp = App.getContext().getSharedPreferences(SpUtil.SP_NAME, Context.MODE_PRIVATE);
    }


    public void setUser(String username)
    {
        if (!TextUtils.isEmpty(username))
        sp.edit().putString(Constant.USER_NAME,username).apply();
    }

    public String getUser()
    {
        return sp.getString(Constant.USER_NAME,"");
    }






    //用户退出登录，删除所有信息
    public void exit() {
        sp.edit().remove(Constant.USER_NAME)
                .apply();
    }




    /**
     * 判断用户是否登录
     *
     * @return
     */
    public boolean isLogin() {
        String username = sp.getString(Constant.USER_NAME, null);
        if (!TextUtils.isEmpty(username) ) {
            return true;
        }
        return false;
    }


}
