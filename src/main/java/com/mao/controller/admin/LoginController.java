package com.mao.controller.admin;

import com.mao.po.User;
import com.mao.service.UserService;
import com.mao.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Author: Administrator
 * Date: 2021/6/26 9:51
 * Description: 登录控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 登录用户
     * @param username
     * @param password
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            if(user.getType() != 1){
                attributes.addFlashAttribute("message","权限不够");
                return "redirect:/admin";
            }
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
