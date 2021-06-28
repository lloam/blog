package com.mao.controller.admin;

import com.mao.po.User;
import com.mao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Author: Administrator
 * Date: 2021/6/28 15:28
 * Description:
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 来到注册界面
     * @return
     */
    @GetMapping("/register/register/register/forSecurity")
    public String toAddUser(Model model){
        User user = new User();
        user.setId(null);
        model.addAttribute("user",user);
        return "admin/register";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register/register/register/addOrUpdateUser")
    public String addUser(User user,
                          RedirectAttributes attributes){
        User userAddOrUp = null;
        if(user.getId()==null){
            // 添加用户
            userAddOrUp = userService.registerUser(user);
            if(userAddOrUp == null){
                attributes.addFlashAttribute("message","注册失败");
                return "redirect:/admin/register/register/register/forSecurity";
            }else {
                System.out.println(userAddOrUp);
                attributes.addFlashAttribute("message","注册成功");
                return "redirect:/admin";
            }
        }else {
            // 更新用户
            userAddOrUp = userService.updateUser(user.getId(),user);
            if(userAddOrUp == null){
                attributes.addFlashAttribute("message","更新失败");
                return "redirect:/admin/";
            }else {
                System.out.println(userAddOrUp);
                attributes.addFlashAttribute("message","更新成功");
                return "redirect:/admin/users";
            }
        }

    }

    @GetMapping("/users")
    public String users(@PageableDefault(size = 2) Pageable pageable,
                        Model model){
        model.addAttribute("pageUser",userService.findAllUser(pageable));
        return "admin/users";
    }

    @GetMapping("/users/update/{id}")
    public String toUpdateUser(@PathVariable("id")Integer id,
                               Model model){
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "admin/register";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id")Integer id,
                             RedirectAttributes attributes){
        userService.deleteUser(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/users";
    }
}
