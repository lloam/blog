package com.mao.web;

import com.mao.NotFoundException;
import com.mao.dto.BlogQuery;
import com.mao.service.BlogService;
import com.mao.service.TagService;
import com.mao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Administrator
 * Date: 2021/6/25 19:58
 * Description: 首页控制器
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listBlogRecommendBlog(8));
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query,
                         Model model){
        model.addAttribute("page",blogService.listBlog("%" + query + "%",pageable));
        model.addAttribute("query",query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id")Integer id,
                       Model model){
        model.addAttribute("blog",blogService.getBlog(id));
        return "blog";
    }
}
