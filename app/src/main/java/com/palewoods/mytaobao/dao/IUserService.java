package com.palewoods.mytaobao.dao;

import com.palewoods.mytaobao.model.User;

/**
 * Created by bsl52840 on 2017/3/15.
 * 用户接口
 */

public interface IUserService {
    public void insert(User user);
    public User getUserById(String userId);
}
