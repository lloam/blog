package com.mao.web.admin;

import com.mao.po.Type;
import com.mao.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Author: Administrator
 * Date: 2021/6/26 11:17
 * Description: 类型控制器
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 分页查询分类
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 来到新增页面
     * @return
     */
    @GetMapping("/types/input")
    public String inputPage(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 通过 post 方式提交 type数据
     * @param type
     * @return
     */
    @PostMapping("/types")
    public String postAddType(@Valid Type type,
                              BindingResult result,
                              RedirectAttributes attributes){
//        System.out.println(type);
//        System.out.println(result.hasErrors());
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            result.rejectValue("name","nameError","已经存在该分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        System.out.println(type);
        Type t = typeService.saveType(type);
        System.out.println(t);
        if(t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 调到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Integer id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    /**
     * put 方式更新数据库中的 type 信息
     * @param type
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PutMapping("types/{id}")
    public String postDeleteType(@Valid Type type,
                              BindingResult result,
                              @PathVariable("id") Integer id,
                              RedirectAttributes attributes){
        Type typeByName = typeService.getTypeByName(type.getName());
        if(typeByName != null){
            result.rejectValue("name","nameError","已经存在该分类");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id,type);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除 type 类型，但是正常情况下我们的方法应该要符合rest风格，应该使用DeleteMapping
     * 这里没有进行处理
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("types/{id}/delete")
    public String deleteType(@PathVariable("id") Integer id,
                             RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }
}
