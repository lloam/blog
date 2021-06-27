package com.mao.web.admin;

import com.mao.po.Tag;
import com.mao.po.Type;
import com.mao.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 分页查询标签
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping("/tags")
    public String types(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                                    Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 来到新增页面
     * @return
     */
    @GetMapping("/tags/input")
    public String inputPage(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 通过 post 方式提交 tag 数据
     * @param tag
     * @param result
     * @param attributes
     * @return
     */
    @PostMapping("/tags")
    public String postAddType(@Valid Tag tag,
                              BindingResult result,
                              RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName != null){
            result.rejectValue("name","nameError","已经存在该分标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        System.out.println(tag);
        Tag t = tagService.saveTag(tag);
        System.out.println(t);
        if(t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Integer id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    /**
     * put 方式更新数据库中的 tag 信息
     * @param tag
     * @param result
     * @param id
     * @param attributes
     * @return
     */
    @PutMapping("tags/{id}")
    public String postDeleteTag(@Valid Tag tag,
                              BindingResult result,
                              @PathVariable("id") Integer id,
                              RedirectAttributes attributes){
        Tag tagByName = tagService.getTagByName(tag.getName());
        if(tagByName != null){
            result.rejectValue("name","nameError","已经存在该标签");
        }
        if(result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除 tag 类型，但是正常情况下我们的方法应该要符合rest风格，应该使用DeleteMapping
     * 这里没有进行处理
     * @param id
     * @param attributes
     * @return
     */
    @GetMapping("tags/{id}/delete")
    public String deleteTag(@PathVariable("id") Integer id,
                             RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
