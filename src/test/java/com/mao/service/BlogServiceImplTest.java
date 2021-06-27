package com.mao.service;

import com.mao.po.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Administrator
 * Date: 2021/6/26 22:00
 * Description:
 */
@SpringBootTest
class BlogServiceImplTest {

    @Autowired
    private BlogService blogService;
    @Test
    void queryBlog(){
        Blog blog = blogService.getBlog(4);
//        System.out.println(blog.isRecommend());
    }
}