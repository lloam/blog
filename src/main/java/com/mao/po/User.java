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
 * Date: 2021/6/25 23:17
 * Description: 用户实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Blog> blogs = new ArrayList<>();

    public User(String nickname, String username, String password, String email, String avatar, Integer type) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.type = type;
    }
}
