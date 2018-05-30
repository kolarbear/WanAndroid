package com.kolarbear.wanandroid.bean.login;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */

public class LoginBean {

    /**
     * id : 211
     * icon :
     * username : kolarbear
     * type : 0
     * password : qwer1234
     * email :
     * collectIds : [1350,4,2490]
     */

    private int id;
    private String icon;
    private String username;
    private int type;
    private String password;
    private String email;
    private List<Integer> collectIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
