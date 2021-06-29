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
 * Date: 2021/6/29 13:01
 * Description: 留言实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    // 留言自关联
    @OneToMany(mappedBy = "parentMessage")
    private List<Message> replyMessage = new ArrayList<>();
    @ManyToOne
    private Message parentMessage;
    // 是否是我自己的的留言
    private boolean adminMessage;
}
