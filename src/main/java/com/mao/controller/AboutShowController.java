package com.mao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author: Administrator
 * Date: 2021/6/28 14:18
 * Description:
 */
@Controller
public class AboutShowController {


    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
