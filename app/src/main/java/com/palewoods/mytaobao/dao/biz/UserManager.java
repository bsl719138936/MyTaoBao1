package com.palewoods.mytaobao.dao.biz;

import com.palewoods.mytaobao.dao.IUserService;
import com.palewoods.mytaobao.daompl.UserService;
import com.palewoods.mytaobao.model.User;

/**
 * Created by bsl52840 on 2017/3/15.
 * 用户的管理类
 */

public class UserManager {
    private IUserService dao;

    public UserManager() {
        dao = new UserService();
    }

//    public User getUserById(String userId) {
//
//    }

    /*登录，成功返回登录成功的用户的实体，否则返回null*/
    public User Login(String userId, String passWord) {
        User user = dao.getUserById(userId);
        if (user != null) {
            return user.getPassWord().equals(passWord) ? user : null;
        }
        return null;
    }

    /*注册，成功返回注册的用户的实体，否则返回null*/
    public User Register(User user) {
        try {
            this.dao.insert(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
