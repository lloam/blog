package com.mao.web;

import com.mao.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author: Administrator
 * Date: 2021/6/25 19:58
 * Description: 首页控制器
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i = 10/0;
//        String blog = null;
//        if(blog == null){
//            throw  new NotFoundException("博客不存在");
//        }
        System.out.println("-------------index------------");
        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
//        int i = 10/0;
//        String blog = null;
//        if(blog == null){
//            throw  new NotFoundException("博客不存在");
//        }
        System.out.println("-------------index------------");
        return "blog";
    }
}
