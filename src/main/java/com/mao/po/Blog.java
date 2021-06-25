package com.mao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: Administrator
 * Date: 2021/6/25 22:57
 * Description: 博客实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "comment '博客标题'")
    private String title;
    @Column(columnDefinition = "comment '博客内容'")
    private String content;
    @Column(columnDefinition = "comment '博客首图'")
    private String firstPicture;
    @Column(columnDefinition = "comment '博客标签'")
    private String flag;
    @Column(columnDefinition = "comment '博客浏览量'")
    private Integer view;
    @Column(columnDefinition = "comment '博客是否开启赞赏'")
    private Boolean appreciation;
    @Column(columnDefinition = "comment '博客是否开启版权'")
    private Boolean shareStatement;
    @Column(columnDefinition = "comment '博客是否开启评论'")
    private Boolean commentable;
    @Column(columnDefinition = "comment '博客是否发布'")
    private Boolean published;
    @Column(columnDefinition = "comment '博客是否推荐'")
    private Boolean recommend;
    @Column(columnDefinition = "comment '博客创建时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(columnDefinition = "comment '博客更新时间'")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // many的一端为关系维护端
    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne()
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
