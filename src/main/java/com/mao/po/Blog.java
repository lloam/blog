package com.mao.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    // 标注这个字段是一个大字段，并且具有懒加载的效果
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;
    private String firstPicture;
    private String flag;
    private Integer view;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private boolean published;
    private boolean recommend;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    // many的一端为关系维护端
    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    // 这个字段不会写入数据库
    @Transient
    private String tagIds;

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
