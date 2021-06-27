package com.mao.dao;

import com.mao.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Administrator
 * Date: 2021/6/26 16:07
 * Description:
 */
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    /**
     * 测试 user 的id是否也会直接继承
     */
    @Test
    public void addUser(){
        User save = userRepository.save(new User("添加user", "添加user", "123456", "2196143404@qq.com", "/images/logo.jpg", 1));
        int i = 0;

    }
}