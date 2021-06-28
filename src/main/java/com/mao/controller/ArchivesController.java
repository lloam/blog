package com.mao.controller;

import com.mao.po.Blog;
import com.mao.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Author: Administrator
 * Date: 2021/6/28 13:45
 * Description:
 */
@Controller
public class ArchivesController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archivesBlog",blogService.archivesBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
