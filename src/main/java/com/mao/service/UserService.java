package com.mao.service;

import com.mao.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 9:47
 * Description:
 */
public interface UserService {

    User findById(Integer id);

    User checkUser(String username,String password);

    User registerUser(User user);

    User updateUser(Integer id,User user);

    Page<User> findAllUser(Pageable pageable);

    void deleteUser(Integer id);
}
