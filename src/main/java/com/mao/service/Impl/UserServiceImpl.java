package com.mao.service.Impl;

import com.mao.NotFoundException;
import com.mao.dao.UserRepository;
import com.mao.po.User;
import com.mao.service.UserService;
import com.mao.util.MD5Utils;
import com.mao.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/26 9:48
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Transactional
    public User registerUser(User user) {
        user.setBlogs(null);
        user.setCreateTime(new Date());
        user.setType(1);
        user.setUpdateTime(new Date());
        // MD5加密保存密码
        user.setPassword(MD5Utils.code(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Integer id,User user) {
        User userRepositoryById = userRepository.getById(id);
        if(userRepositoryById==null){
            throw new NotFoundException("没有该用户");
        }
        BeanUtils.copyProperties(user,userRepositoryById, MyBeanUtils.getNullPropertyNames(user));
        userRepositoryById.setPassword(MD5Utils.code(userRepositoryById.getPassword()));
        return userRepository.save(userRepositoryById);
    }


    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
