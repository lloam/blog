package com.mao.dao;

import com.mao.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.rmi.runtime.Log;

/**
 * Author: Administrator
 * Date: 2021/6/26 9:48
 * Description:
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);
}
