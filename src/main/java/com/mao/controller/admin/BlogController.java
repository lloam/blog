package com.mao.controller.admin;

import com.mao.dto.BlogQuery;
import com.mao.po.Blog;
import com.mao.po.User;
import com.mao.service.BlogService;
import com.mao.service.CommentService;
import com.mao.service.TagService;
import com.mao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * Author: Administrator
 * Date: 2021/6/26 10:46
 * Description: 博客控制器
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    /**
     * 获取所有的博客，并且分页
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blogQuery,
                        Model model){
//        System.out.println(blogQuery);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return "admin/blogs";
    }

    /**
     * 搜索博客
     * @param pageable
     * @param blogQuery
     * @param model
     * @return
     */
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         BlogQuery blogQuery,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        return "admin/blogs :: blogList";
    }

    private void getTypesAndTags(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }
    /**
     * 来到新增博客的页面
     * @param model
     * @return
     */
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        getTypesAndTags(model);
        return "admin/blogs-input";
    }

    /**
     * 修改或者添加博客
     * @param blog
     * @param attributes
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public String addOrUpdateBlog(Blog blog,
                                  RedirectAttributes attributes,
                                  HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
//        System.out.println(blog);
        blog.setType(typeService.getType(blog.getType().getId()));
        // 接收到的前端的 id 类似于 1,2,3
        blog.setTags(tagService.listTag(blog.getTagIds()));
        Blog b;
        // 如果穿过来的blog没有id，说明是添加
        if(blog.getId() == null){
            b = blogService.saveBlog(blog);
        }else {
            // 有 id 值，说明是修改
            b = blogService.updateBlog(blog.getId(),blog);
        }
        if(b == null){
            attributes.addFlashAttribute("message","操作失败");
        }else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/blogs";
    }

    /**
     * 来到修改页面，和添加供一个页面，本来修改应该用PutMapping的
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Integer id,
                            Model model){
        getTypesAndTags(model);
        Blog blog = blogService.getBlog(id);
        // 初始化 tagIds，一开始tagIds是空的
        blog.init();
        model.addAttribute("blog",blogService.getBlog(id));
        return "admin/blogs-input";
    }

    /**
     * 删除博客
     * 这里需要注意的是删除博客的同时，评论需要先删除，因为 blog 的主键作为了 comment 的外键。如果单单删除了 blog ，那么评论的外键就没有了
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes attributes){
        commentService.deleteComment(id);
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
