package com.mao.controller;

import com.mao.po.Comment;
import com.mao.po.User;
import com.mao.service.BlogService;
import com.mao.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/27 21:53
 * Description:
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService blogService;
    // 获取回复的头像
    @Value("${comment.avatar}")
    private String avatar;
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Integer blogId,
                           Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        List<Comment> commentViews = eachComment(comments);
        model.addAttribute("comments",commentViews);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        // 将要评论那一条博客根据 id 查询出来，因为此时 comment 里面的 blog 只有 id 值
        Integer blogId = comment.getBlog().getId();
        // 设置回复的博客
        comment.setBlog(blogService.getBlog(blogId));
        // 设置是否为管理员回复
        User user = (User) session.getAttribute("user");
        if(user != null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            // 设置回复头像
            comment.setAvatar(avatar);
        }
        // 保存评论
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    /**
     * 循环每个顶级的评论节点，避免等下修改数据影响到数据库中的数据
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中,将回复评论全部放到另一个集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {

        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComments();
            for(Comment reply1 : replys1) {
                // 循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            // 修改顶级节点的reply集合为迭代处理后的集合，其实就是将评论的树级关系转换成了层级关系（只有两层的层级关系）
            comment.setReplyComments(tempReplys);
            // 清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    // 存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱，将所有回复评论都放到一个集合中
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);// 顶节点添加到临时存放集合
        if (comment.getReplyComments().size()>0) {
            // 如果子回复评论还有回复评论，也放到同一个集合中
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0) {
                    // 递归将子回复评论放到集合当中
                    recursively(reply);
                }
            }
        }
    }

}
