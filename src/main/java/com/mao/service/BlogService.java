package com.mao.service;

import com.mao.dto.BlogQuery;
import com.mao.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Author: Administrator
 * Date: 2021/6/26 15:46
 * Description:
 */
public interface BlogService {

    Blog getBlog(Integer id);

    Blog getAndConvert(Integer id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable,Integer tagId);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    List<Blog> listBlogRecommendBlog(Integer size);

    Map<String,List<Blog>> archivesBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Integer id,Blog blog);

    void deleteBlog(Integer id);

    Blog getBlogAboutMe(String title);
}
