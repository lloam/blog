# springboot开发的个人博客

# 1、需求与功能

用户故事

## 1.1用户故事

用户故事模板：

- 作为一个（某个角色）使用者，我可以做某个功能事情，如此可以有（某个商业价值）的好处

角色、功能、商业价值



个人博客系统的用户故事：

角色：普通访客，管理员（我）

- 访客：可以分页查看所有博客
- 访客，可以快速查看博客数最多的6个分类
- 访客：可以查看所有分类
- 访客：可以查看某个分类下的博客列表
- 访客：可以快速查看标记博客最多的10个标签
- 访客：可以查看所有的标签
- 访客：可以查看某个标签下的博客列表
- 访客：可以根据年度时间线查看博客列表
- 访客：可以快速查看最新的推荐博客
- 访客：可以查看单个博客内容
- 访客：可以对博客进行评论
- 访客：可以赞赏博客内容
- 访客：可以微信扫码阅读博客内容
- 访客：可以在首页扫描公众号二维码关注我

---

- 我：可以用户名和密码登录后台管理
- 我：可以管理博客
  - 我：可以发布新的博客
  - 我：可以对博客进行分类
  - 我：可以对博客打标签
  - 我：可以修改博客
  - 我：可以删除博客
  - 我：可以根据标题，分类，标签查询博客
- 我：可以管理博客分类
  - 我：可以新增一个分类
  - 我：可以修改一个分类
  - 可以删除一个分类
  - 我：可以根据分类名称查询分类
- 我：可以管理标签
  - 我：可以新增一个标签
  - 我：可修改一个标签
  - 我：可以删除一个标签
  - 我：可以根据名称查询标签

## 1.2 功能规划

![image-20210619212011900](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210619212011900.png)



# 2、页面设计与开发

## 2.1 设计

![image-20210619212011900](blog.assets/image-20210619212011900.png)

前端展示：首页、详情页、分类、标签、归档、关于我

后台管理：模板页





# 3、框架搭建

## 3.1 构建与配置

1、引入Spring Boot 模块

- web
- Thymeleaf
- JPA
- MySQL
- Aspects
- DevTools
- redis
- 构建日志、开发与生产环境的配置

## 3.2 异常处理

1、定义错误页面

- 404
- 500
- error

2、全局处理异常

- 如果发生系统异常，则跳转到error页面

- 如果发生了我们自定义的页面未找到异常就跳转到 404 页面



## 3.3 日志处理

采用 aop 横向切面切入日志

1、记录日志内容

- 请求 url
- 访问者 ip
- 调用方法 classMethod
- 参数 args
- 返回的内容

2、记录日志类



## 3.4 页面处理

1、静态页面导入project

遇到的问题，会有静态资源访问不了得问题，需要编写配置类，让Spring Boot不要拦截静态资源

```java
package com.mao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: Administrator
 * Date: 2021/6/25 21:38
 * Description:
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}

```

2、thymeleaf布局

- 定义 fragment
- 使用 fragment 布局

3、错误页面美化



# 4、设计与规范

## 4.1 实体设计

实体类：

- 博客 Blog
- 博客分类 Type
- 博客标签 Tag
- 博客评论 Comment
- 用户 User



实体关系：

![image-20210625224358163](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625224358163.png)



评论类自关联：

![image-20210625224842372](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625224842372.png)



Blog 类：

![image-20210625224922901](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625224922901.png)



Type 类：

![image-20210625225106431](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625225106431.png)



Tag 类：

![image-20210625225127277](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625225127277.png)



Comment 类：

![image-20210625225152202](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625225152202.png)



User 类：

![image-20210625225216552](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625225216552.png)



## 4.2 应用分层

![image-20210625225305772](https://gitee.com/lloamhh/spring-img/raw/master/img/blog/image-20210625225305772.png)



## 4.3 命名约定

Service/DAO 层命名约定:

- 获取单个对象的方法用 get 做前缀
- 获取多个对象的方法用 list 做前缀
- 获取统计值的方法用 count 做前缀
- 插入的方法用 save（推荐）或 insert 左前缀
- 删除的方法用 remove（推荐）或 delete 做前缀
- 修改的方法用 update 做前缀



# 5、后台管理



## 5.1 登录

1、构建登录页面和后台管理首页

2、UserService 和UserRepository

3、LoginController 实现登录

4、MD5 加密

5、登录拦截器



## 5.2 分类管理

1、分类管理页面

2、分类列表分页

```json
{
    "content":[
        {"id":123,"title":"blog122","content":"this is blog content"},
        {"id":122,"title":"blog121","content":"this is blog content"},
        {"id":121,"title":"blog120","content":"this is blog content"},
        {"id":120,"title":"blog119","content":"this is blog content"},
        {"id":119,"title":"blog118","content":"this is blog content"},
        {"id":118,"title":"blog117","content":"this is blog content"},
        {"id":117,"title":"blog116","content":"this is blog content"},
        {"id":116,"title":"blog115","content":"this is blog content"},
        {"id":115,"title":"blog114","content":"this is blog content"},
        {"id":114,"title":"blog113","content":"this is blog content"},
        {"id":113,"title":"blog112","content":"this is blog content"},
        {"id":112,"title":"blog111","content":"this is blog content"},
        {"id":111,"title":"blog110","content":"this is blog content"},
        {"id":110,"title":"blog109","content":"this is blog content"},
        {"id":109,"title":"blog108","content":"this is blog content"},
    ],
    "last":false,
    "totalPages":9,
    "size":15,
    "number":0,
    "first":true,
    "sort":[{
        "direction":"DESC",
        "property":"id",
        "ignoreCase":false,
        "nullHandling":"NATIVE",
        "ascending":false
    }],
    "numberOfElements":15
}
```



3、分类新增、修改、删除









==commonmark：将 markdown 语法转换成 html 的插件==











