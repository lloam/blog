package com.mao.service;

import com.mao.dao.CommentRepository;
import com.mao.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/27 21:58
 * Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> listCommentByBlogId(Integer blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
        return commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        Integer parentCommentId = comment.getParentComment().getId();
        if(parentCommentId != -1){
            comment.setParentComment(commentRepository.getById(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 根据 blogId 删除评论
     * @param blogId
     */
    @Transactional
    public void deleteComment(Integer blogId) {
        commentRepository.deleteCommentByBlogId(blogId);
    }

    /**
     * 获取总评论数
     * @return
     */
    public long commentCount() {
        return commentRepository.count();
    }
}
