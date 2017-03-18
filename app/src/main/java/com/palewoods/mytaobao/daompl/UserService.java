package com.palewoods.mytaobao.daompl;

import com.palewoods.mytaobao.dao.IUserService;
import com.palewoods.mytaobao.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bsl52840 on 2017/3/15.
 * 用户的实现类
 */

public class UserService implements IUserService{

    private Map<String,User> users = null;

    public UserService() {
        users = new HashMap<String, User>();
        User user = new User("admin","admin");
        this.insert(user);
    }

    @Override
    public void insert(User user) {
        users.put(user.getUserId(),user);
    }

    @Override
    public User getUserById(String userId) {
        return users.get(userId);
    }
}
