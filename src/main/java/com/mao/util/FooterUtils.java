package com.mao.util;

import com.mao.service.BlogService;
import com.mao.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

/**
 * Author: Administrator
 * Date: 2021/6/28 20:54
 * Description:
 */
@Component
public class FooterUtils {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisUtil redisUtil;

    public void getCount(Model model){
        // 只要进入首页或者登录页都要进行 redis 中 view的增加
        redisUtil.incr("views",1);
        // 然后再将views取出来
        Integer views = (Integer) redisUtil.get("views");
        model.addAttribute("views",views);
        model.addAttribute("blogCount",blogService.countBlog());
        model.addAttribute("commentCount",commentService.commentCount());
    }

    public void getRecommendBlog(Model model){
        model.addAttribute("newBlogs",blogService.listBlogRecommendBlog(3));
    }
}
