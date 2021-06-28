package com.mao.controller;

import com.mao.po.Blog;
import com.mao.service.BlogService;
import com.mao.service.TagService;
import com.mao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Administrator
 * Date: 2021/6/28 14:18
 * Description:
 */
@Controller
public class AboutShowController {


    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Value("${me.github}")
    private String github;

    @GetMapping("/about")
    public String about(@RequestParam("title") String title,
                        Model model){
        model.addAttribute("aboutMe",blogService.getBlogAboutMe(title));
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("github",github);
        return "about";
    }
}
