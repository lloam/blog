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
 * Date: 2021/6/25 23:12
 * Description: 博客评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @ManyToOne
    private Blog blog;

    // comment 自关联，将回复的评论看成是一条记录，那么就是一对多的关系
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();
    @ManyToOne
    private Comment parentComment;
}
