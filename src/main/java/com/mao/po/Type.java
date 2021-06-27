package com.mao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/25 23:10
 * Description: 博客分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_type")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "分类名称不能为空")
    private String name;

    // 允许懒加载
    @OneToMany(mappedBy = "type",fetch = FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<>();
}
