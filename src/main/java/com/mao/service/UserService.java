package com.mao.service;

import com.mao.po.User;

/**
 * Author: Administrator
 * Date: 2021/6/26 9:47
 * Description:
 */
public interface UserService {

    User checkUser(String username,String password);
}
