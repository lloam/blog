package com.mao.controller;
import com.mao.po.Message;
import com.mao.po.User;
import com.mao.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Administrator
 * Date: 2021/6/29 13:16
 * Description:
 */
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;
    // 获取回复的头像
    @Value("${comment.avatar}")
    private String avatar;
    @GetMapping("/messages")
    public String message(@PageableDefault(size = 10,sort = "createTime",direction = Sort.Direction.DESC) Pageable pageable,
                          Model model){
        Page<Message> page = messageService.queryMessage(pageable);
        model.addAttribute("page",page);
        return "message";
    }

    @PostMapping("/addMessage")
    public String addMessage(Message message, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user != null){
            message.setAdminMessage(true);
            message.setAvatar(user.getAvatar());
        }else {
            message.setAdminMessage(false);
            message.setAvatar(avatar);
        }
        // 保存评论
        messageService.addMessage(message);
        return "redirect:/messages/ok";
    }

    @GetMapping("/messages/ok")
    public String messageOk(@PageableDefault(size = 10,sort = "createTime",direction = Sort.Direction.DESC) Pageable pageable,
                            Model model){
        Page<Message> page = messageService.queryMessage(pageable);
        model.addAttribute("page",page);
        return "message :: messageList";
    }
}
