package com.palewoods.mytaobao.model;

/**
 * Created by bsl52840 on 2017/3/15.
 * 用户类
 */

public class User {
    private String userId;
    private String passWord;

    public User() {
    }

    public User(String userId, String passWord) {
        this.userId = userId;
        this.passWord = passWord;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
