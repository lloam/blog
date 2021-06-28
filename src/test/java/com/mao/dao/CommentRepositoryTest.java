package com.mao.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Administrator
 * Date: 2021/6/28 15:01
 * Description:
 */
@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Test
    void deleteCommentByBlogId() {
        Integer result = commentRepository.deleteCommentByBlogId(20);
        System.out.println(result);
    }
}