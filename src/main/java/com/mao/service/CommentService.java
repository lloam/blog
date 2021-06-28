package com.mao.service;

import com.mao.po.Comment;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/27 21:57
 * Description:
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Integer id);

    Comment saveComment(Comment comment);


    void deleteComment(Integer blogId);
}
