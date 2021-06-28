package com.mao.dao;

import com.mao.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/27 21:58
 * Description:
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    List<Comment> findByBlogIdAndParentCommentNull(Integer blogId, Sort sort);

    @Modifying
    @Query("delete from Comment c where c.blog.id = ?1")
    Integer deleteCommentByBlogId(Integer blogId);

    long count();
}
